package es.unex.gc01.usersservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/follows")
public class FollowController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Seguir a un artista
    @PostMapping
    public ResponseEntity<?> followArtist(@RequestParam String followerUsername,
                                          @RequestParam String followedUsername) {
        try {
            System.out.println("=== INTENTANDO SEGUIR ARTISTA ===");
            System.out.println("followerUsername: " + followerUsername);
            System.out.println("followedUsername: " + followedUsername);

            // Verificar si es el mismo usuario
            if (followerUsername.equals(followedUsername)) {
                return ResponseEntity.status(400).body(Map.of(
                        "error", "Bad Request",
                        "message", "No puedes seguirte a ti mismo",
                        "status", 400,
                        "timestamp", Instant.now().toString()
                ));
            }

            // Verificar si ya sigue al artista
            String checkSql = "SELECT COUNT(*) FROM user_follows WHERE follower_username = ? AND followed_username = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, followerUsername, followedUsername);

            System.out.println("Ya sigue al artista? " + (count > 0));

            if (count > 0) {
                return ResponseEntity.status(409).body(Map.of(
                        "error", "Conflict",
                        "message", "Ya sigues a este artista",
                        "status", 409,
                        "timestamp", Instant.now().toString()
                ));
            }

            // Verificar que el artista existe y no está eliminado
            String checkArtistSql = "SELECT COUNT(*) FROM users WHERE username = ? AND deleted = false";
            Integer artistExists = jdbcTemplate.queryForObject(checkArtistSql, Integer.class, followedUsername);

            if (artistExists == 0) {
                return ResponseEntity.status(404).body(Map.of(
                        "error", "Not Found",
                        "message", "Artista no encontrado",
                        "status", 404,
                        "timestamp", Instant.now().toString()
                ));
            }

            // Insertar el seguimiento
            String insertSql = "INSERT INTO user_follows (follower_username, followed_username) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, followerUsername, followedUsername);

            // Obtener el contador actual (contar TODOS los seguidores, incluso usuarios eliminados)
            String countSql = "SELECT COUNT(*) FROM user_follows WHERE followed_username = ?";
            Integer followersCount = jdbcTemplate.queryForObject(countSql, Integer.class, followedUsername);

            // Actualizar contador en la tabla users
            String updateSql = "UPDATE users SET followers_count = ? WHERE username = ?";
            jdbcTemplate.update(updateSql, followersCount, followedUsername);

            System.out.println("Seguimiento exitoso. Nuevo contador: " + followersCount);

            return ResponseEntity.ok(Map.of(
                    "message", "Ahora sigues a " + followedUsername,
                    "status", 200,
                    "followersCount", followersCount
            ));

        } catch (Exception e) {
            System.err.println("ERROR en followArtist: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al seguir al artista: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Dejar de seguir a un artista
    @DeleteMapping
    public ResponseEntity<?> unfollowArtist(@RequestParam String followerUsername,
                                            @RequestParam String followedUsername) {
        try {
            // Verificar si sigue al artista (sin importar si el usuario está eliminado)
            String checkSql = "SELECT COUNT(*) FROM user_follows WHERE follower_username = ? AND followed_username = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, followerUsername, followedUsername);

            if (count == 0) {
                return ResponseEntity.status(404).body(Map.of(
                        "error", "Not Found",
                        "message", "No sigues a este artista",
                        "status", 404,
                        "timestamp", Instant.now().toString()
                ));
            }

            // Eliminar el seguimiento
            String deleteSql = "DELETE FROM user_follows WHERE follower_username = ? AND followed_username = ?";
            jdbcTemplate.update(deleteSql, followerUsername, followedUsername);

            // Obtener el nuevo contador (contar TODOS los seguidores)
            String countSql = "SELECT COUNT(*) FROM user_follows WHERE followed_username = ?";
            Integer followersCount = jdbcTemplate.queryForObject(countSql, Integer.class, followedUsername);

            // Actualizar contador en la tabla users
            String updateSql = "UPDATE users SET followers_count = ? WHERE username = ?";
            jdbcTemplate.update(updateSql, followersCount, followedUsername);

            System.out.println("Dejar de seguir exitoso. Nuevo contador: " + followersCount);

            return ResponseEntity.ok(Map.of(
                    "message", "Has dejado de seguir a " + followedUsername,
                    "status", 200,
                    "followersCount", followersCount
            ));

        } catch (Exception e) {
            System.err.println("ERROR en unfollowArtist: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al dejar de seguir al artista: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Obtener artistas seguidos por un usuario
    @GetMapping("/following/{username}")
    public ResponseEntity<?> getFollowingArtists(@PathVariable String username) {
        try {
            String sql = """
                SELECT u.username, u.name, u.bio, 
                       (SELECT COUNT(*) FROM user_follows uf2 WHERE uf2.followed_username = u.username) as followers_count,
                       GROUP_CONCAT(DISTINCT ug.genre) as genres
                FROM user_follows uf
                JOIN users u ON uf.followed_username = u.username
                LEFT JOIN user_genres ug ON u.id = ug.user_id
                WHERE uf.follower_username = ? AND u.deleted = false
                GROUP BY u.username, u.name, u.bio
                """;

            List<Map<String, Object>> artists = jdbcTemplate.queryForList(sql, username);

            return ResponseEntity.ok(artists);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al obtener artistas seguidos: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Obtener seguidores de un artista
    @GetMapping("/followers/{artistUsername}")
    public ResponseEntity<?> getArtistFollowers(@PathVariable String artistUsername) {
        try {
            String sql = """
                SELECT u.username, u.name, u.email
                FROM user_follows uf
                JOIN users u ON uf.follower_username = u.username
                WHERE uf.followed_username = ? AND u.deleted = false
                """;

            List<Map<String, Object>> followers = jdbcTemplate.queryForList(sql, artistUsername);

            return ResponseEntity.ok(followers);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al obtener seguidores: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Verificar si un usuario sigue a un artista
    @GetMapping("/is-following")
    public ResponseEntity<?> isFollowing(@RequestParam String followerUsername,
                                         @RequestParam String followedUsername) {
        try {
            String sql = "SELECT COUNT(*) FROM user_follows WHERE follower_username = ? AND followed_username = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, followerUsername, followedUsername);

            return ResponseEntity.ok(Map.of("isFollowing", count > 0));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al verificar seguimiento: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Obtener número de seguidores de un artista (CONTAR TODOS, incluso usuarios eliminados)
    @GetMapping("/followers-count/{artistUsername}")
    public ResponseEntity<?> getFollowersCount(@PathVariable String artistUsername) {
        try {
            // Contar en tiempo real desde la tabla user_follows (TODOS los seguidores)
            String countSql = "SELECT COUNT(*) FROM user_follows WHERE followed_username = ?";
            Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, artistUsername);

            return ResponseEntity.ok(Map.of("followersCount", count != null ? count : 0));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al obtener contador de seguidores: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Obtener artistas más seguidos (tendencia) - CONTANDO TODOS los seguidores
    @GetMapping("/trending")
    public ResponseEntity<?> getTrendingArtists(@RequestParam(defaultValue = "10") int limit) {
        try {
            String sql = """
                SELECT u.username, u.name, u.bio,
                       COUNT(uf.id) as followers_count,
                       GROUP_CONCAT(DISTINCT ug.genre) as genres
                FROM users u
                LEFT JOIN user_follows uf ON u.username = uf.followed_username
                LEFT JOIN user_genres ug ON u.id = ug.user_id
                WHERE u.deleted = false  -- Solo artistas activos
                GROUP BY u.username, u.name, u.bio
                HAVING COUNT(uf.id) > 0
                ORDER BY followers_count DESC
                LIMIT ?
                """;

            List<Map<String, Object>> trendingArtists = jdbcTemplate.queryForList(sql, limit);

            return ResponseEntity.ok(trendingArtists);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al obtener artistas en tendencia: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Endpoint para obtener todos los artistas
    @GetMapping("/all-artists")
    public ResponseEntity<?> getAllArtists() {
        try {
            String sql = """
                SELECT u.username, u.name, u.bio, 
                       COALESCE(u.followers_count, 0) as followers_count,
                       GROUP_CONCAT(DISTINCT ug.genre) as genres
                FROM users u
                LEFT JOIN user_genres ug ON u.id = ug.user_id
                WHERE u.role = 'ARTIST' AND u.deleted = false
                GROUP BY u.username, u.name, u.bio, u.followers_count
                """;

            List<Map<String, Object>> artists = jdbcTemplate.queryForList(sql);

            System.out.println("=== ARTISTAS ENCONTRADOS: " + artists.size() + " ===");
            artists.forEach(artist -> {
                System.out.println("Artista: " + artist.get("username"));
            });

            return ResponseEntity.ok(artists);

        } catch (Exception e) {
            System.err.println("ERROR en getAllArtists: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al obtener artistas: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    // Sincronizar contadores (para arreglar datos existentes)
    @PostMapping("/sync-counters")
    public ResponseEntity<?> syncFollowersCounters() {
        try {
            String sql = """
                UPDATE users u 
                SET followers_count = (
                    SELECT COUNT(*) 
                    FROM user_follows uf 
                    WHERE uf.followed_username = u.username
                )
                WHERE u.deleted = false
                """;

            int updated = jdbcTemplate.update(sql);

            return ResponseEntity.ok(Map.of(
                    "message", "Contadores sincronizados correctamente",
                    "usersUpdated", updated,
                    "status", 200
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Error al sincronizar contadores: " + e.getMessage(),
                    "status", 500,
                    "timestamp", Instant.now().toString()
            ));
        }
    }
}
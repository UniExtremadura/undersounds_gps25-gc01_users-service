package es.unex.gc01.usersservice.controllers;
import es.unex.gc01.usersservice.model.User;
import es.unex.gc01.usersservice.model.enums.GenreType;
import es.unex.gc01.usersservice.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import es.unex.gc01.usersservice.dto.*;
import es.unex.gc01.usersservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.unex.gc01.usersservice.model.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint para microservicios por USERNAME



    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            // Tu código actual para obtener el usuario básico
            UserProfileDTO user = userService.getUserForMicroservice(username);

            // OBTENER LOS GÉNEROS DE LA TABLA user_genres
            String sqlGenres = "SELECT genre FROM user_genres WHERE user_id = ?";
            List<String> genres = jdbcTemplate.queryForList(sqlGenres, String.class, user.getUsername());
            user.setFavoriteGenres(genres);

            // DEBUG: Mostrar qué datos se están enviando
            System.out.println("=== DATOS ENVIADOS AL FRONTEND ===");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Bio: '" + user.getBio() + "'");
            System.out.println("Phone: '" + user.getPhone() + "'");
            System.out.println("FavoriteGenres: " + user.getFavoriteGenres());
            System.out.println("===================================");

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "Usuario no encontrado",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }





    // Endpoint para microservicios por ID
    @GetMapping("/users/id/{public-id}")
    public ResponseEntity<?> getUserById(@PathVariable("public-id") Long id) {
        try {
            UserProfileDTO user = userService.getUserProfileById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "Usuario no encontrado",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }

    // Login para microservicios
    @GetMapping("/users/login/acceso")
    public ResponseEntity<?> getUserLogin(@RequestParam String email,
                                          @RequestParam String password) {
        try {
            UserProfileDTO user = userService.getUserLoginForMicroservice(email, password);


            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Unauthorized",
                    "Credenciales no válidas",
                    401,
                    Instant.now().toString()
            );
            return ResponseEntity.status(401).body(error);
        }
    }

    // Obtener usuario autenticado
    @GetMapping("/users")
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("username") String username) {
        try {
            UserProfileDTO user = userService.getUserProfileByUsername(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Not Found",
                    "Usuario no encontrado",
                    404,
                    Instant.now().toString()
            );
            return ResponseEntity.status(404).body(error);
        }
    }

    // Registro público
    @PostMapping("/users/registro")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userDTO) {

        try {
            UserProfileDTO createdUser = userService.registerUser(userDTO);

            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Conflict",
                    e.getMessage(),
                    409,
                    Instant.now().toString()
            );
            return ResponseEntity.status(409).body(error);
        }
    }

    // Login público
    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
        try {
            UserProfileDTO user = userService.authenticateUser(loginDTO);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Unauthorized",
                    "Credenciales incorrectas",
                    401,
                    Instant.now().toString()
            );
            return ResponseEntity.status(401).body(error);
        }
    }


    // Eliminar usuario (borrado lógico)
    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUser(@RequestHeader("username") String username) {
        try {
            SuccessfulResponseDTO response = userService.deleteUser(username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Bad Request",
                    "Usuario no encontrado o ya eliminado",
                    400,
                    Instant.now().toString()
            );
            return ResponseEntity.status(400).body(error);
        }
    }


    /**
     * Obtener todos los usuarios (para administración)
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PatchMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO,
                                        @RequestHeader("username") String username) {
        try {
            // Buscar el usuario por username
            String sqlSelect = "SELECT id, username, name, surname, role, email, bio, phone, birthday, personal_link FROM users WHERE username = ?";

            List<Map<String, Object>> result = jdbcTemplate.queryForList(sqlSelect, username);

            if (result.isEmpty()) {
                ErrorResponse error = new ErrorResponse(
                        "Not Found",
                        "Usuario no encontrado",
                        404,
                        Instant.now().toString()
                );
                return ResponseEntity.status(404).body(error);
            }

            Map<String, Object> row = result.get(0);
            Long userId = (Long) row.get("id");

            // DEBUG: Mostrar datos recibidos
            System.out.println("=== DATOS RECIBIDOS PARA ACTUALIZAR ===");
            System.out.println("Bio: '" + updateUserDTO.getBio() + "'");
            System.out.println("Phone: '" + updateUserDTO.getPhone() + "'");
            System.out.println("FavoriteGenres: " + updateUserDTO.getFavoriteGenres());
            System.out.println("========================================");

            // Actualizar campos básicos
            String role = updateUserDTO.getRole() != null ? updateUserDTO.getRole().toString() : (String) row.get("role");
            String name = updateUserDTO.getName() != null ? updateUserDTO.getName() : (String) row.get("name");
            String surname = updateUserDTO.getSurname() != null ? updateUserDTO.getSurname() : (String) row.get("surname");
            String email = updateUserDTO.getEmail() != null ? updateUserDTO.getEmail() : (String) row.get("email");
            String bio = updateUserDTO.getBio() != null ? updateUserDTO.getBio() : (String) row.get("bio");
            String phone = updateUserDTO.getPhone() != null ? updateUserDTO.getPhone() : (String) row.get("phone");
            String birthday = updateUserDTO.getBirthday() != null ? updateUserDTO.getBirthday() : (String) row.get("birthday");
            String personalLink = updateUserDTO.getPersonalLink() != null ? updateUserDTO.getPersonalLink() : (String) row.get("personal_link");

            // Actualizar usuario en la tabla users
            String sqlUpdate = "UPDATE users SET " +
                    "role = ?, name = ?, surname = ?, email = ?, bio = ?, phone = ?, birthday = ?, personal_link = ? " +
                    "WHERE id = ?";

            jdbcTemplate.update(sqlUpdate,
                    role,
                    name,
                    surname,
                    email,
                    bio,
                    phone,
                    birthday,
                    personalLink,
                    userId);

            // ACTUALIZAR GÉNEROS FAVORITOS - si vienen en el DTO
            if (updateUserDTO.getFavoriteGenres() != null && !updateUserDTO.getFavoriteGenres().isEmpty()) {
                // Primero eliminar géneros existentes
                String sqlDeleteGenres = "DELETE FROM user_genres WHERE user_id = ?";
                jdbcTemplate.update(sqlDeleteGenres, userId);

                // Insertar nuevos géneros - CONVERTIR A STRING EXPLÍCITAMENTE
                String sqlInsertGenre = "INSERT INTO user_genres (user_id, genre) VALUES (?, ?)";
                for (Object genreObj : updateUserDTO.getFavoriteGenres()) {
                    String genre = genreObj.toString(); // CONVERTIR A STRING
                    jdbcTemplate.update(sqlInsertGenre, userId, genre);
                    System.out.println("Insertando género: " + genre);
                }

                System.out.println("Géneros actualizados: " + updateUserDTO.getFavoriteGenres());
            }

            return ResponseEntity.ok("Usuario actualizado correctamente");

        } catch (Exception e) {
            System.err.println("ERROR en updateUser: " + e.getMessage());
            e.printStackTrace();

            ErrorResponse error = new ErrorResponse(
                    "Internal Server Error",
                    "Error al actualizar el usuario: " + e.getMessage(),
                    500,
                    Instant.now().toString()
            );
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Obtener todos los usuarios (para administración)
     * Con recuperación de datos faltantes usando JdbcTemplate
     */
    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserAdminDTO> users = userService.getAllUsers();

            List<UserAdminDTO> completedUsers = new ArrayList<>();

            for (UserAdminDTO user : users) {
                if (user.getUsername() == null || user.getName() == null) {
                    try {
                        // Consulta SQL con JdbcTemplate
                        String sql = "SELECT username, name, surname, role FROM users WHERE email = ?";

                        Map<String, Object> result = jdbcTemplate.queryForMap(sql, user.getEmail());

                        if (result != null) {
                            UserAdminDTO completedUser = new UserAdminDTO();
                            completedUser.setEmail(user.getEmail());
                            completedUser.setUsername((String) result.get("username"));
                            completedUser.setName((String) result.get("name"));
                            completedUser.setSurname((String) result.get("surname"));
                            completedUser.setRole((String) result.get("role"));
                            completedUsers.add(completedUser);
                        } else {
                            completedUsers.add(user);
                        }
                    } catch (Exception e) {
                        completedUsers.add(user);
                    }
                } else {
                    completedUsers.add(user);
                }
            }

            return ResponseEntity.ok(completedUsers);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(
                    "Internal Server Error",
                    "Error al obtener usuarios: " + e.getMessage(),
                    500,
                    Instant.now().toString()
            );
            return ResponseEntity.status(500).body(error);
        }
    }



    /**
     * Cambiar rol de usuario (para administración)
     */
    @PatchMapping("/users/{username}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable String username,
                                            @RequestBody ChangeRoleDTO changeRoleDTO) {
        try {
            SuccessfulResponseDTO response = userService.changeUserRole(username, String.valueOf(changeRoleDTO.getRole()));
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Bad Request",
                    e.getMessage(),
                    400,
                    Instant.now().toString()
            );
            return ResponseEntity.status(400).body(error);
        }
    }

    /**
     * Eliminar usuario por administrador
     */
    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable String username) {
        try {
            SuccessfulResponseDTO response = userService.deleteUser(username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse(
                    "Bad Request",
                    e.getMessage(),
                    400,
                    Instant.now().toString()
            );
            return ResponseEntity.status(400).body(error);
        }

    }}


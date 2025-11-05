package es.unex.gc01.usersservice.repository;

import es.unex.gc01.usersservice.model.Artist;
import es.unex.gc01.usersservice.model.enums.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByUserUsernameAndDeletedFalse(String username);
    List<Artist> findByArtisticNameContainingIgnoreCaseAndDeletedFalse(String name);
    List<Artist> findByGenresInAndDeletedFalse(Set<GenreType> genres);
    List<Artist> findByIsTrendingTrueAndDeletedFalse();
    Optional<Artist> findByUserIdAndDeletedFalse(Long userId);

    // CORRECCIÓN: Consulta JPQL arreglada
    @Query("SELECT a FROM Artist a WHERE a.deleted = false AND " +
            "(:name IS NULL OR LOWER(a.artisticName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:genres IS NULL OR EXISTS (SELECT g FROM a.genres g WHERE g IN :genres))")
    List<Artist> findArtistsWithFilters(@Param("name") String name,
                                        @Param("genres") Set<GenreType> genres);
}
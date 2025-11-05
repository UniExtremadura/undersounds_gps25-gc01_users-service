
package es.unex.gc01.usersservice.repository;

import es.unex.gc01.usersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndDeletedFalse(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByIdAndDeletedFalse(Long id);


    List<User> findAllByDeletedFalse();
}
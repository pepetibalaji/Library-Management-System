package auth.service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}

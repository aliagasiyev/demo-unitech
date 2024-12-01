package az.edu.turing.msuser.domain.repository;

import az.edu.turing.msuser.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByFin(String fin);

    Optional<UserEntity> findByEmail(String email);
}

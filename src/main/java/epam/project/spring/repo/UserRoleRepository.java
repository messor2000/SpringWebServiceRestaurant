package epam.project.spring.repo;

import epam.project.spring.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT r FROM UserRole r where r.role = :role")
    Optional<UserRole> findByName(@Param("role") String role);
}

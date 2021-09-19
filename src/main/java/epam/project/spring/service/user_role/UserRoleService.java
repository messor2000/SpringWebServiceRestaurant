package epam.project.spring.service.user_role;

import epam.project.spring.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface UserRoleService {
    Optional<UserRole> findUserRoleById(Long id);

    List<UserRole> findAllUserRoles();

    Optional<UserRole> findUserRoleByName(String role);

    boolean create(UserRole role);
}

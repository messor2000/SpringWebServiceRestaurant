package epam.project.spring.service.user;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface UserService {
    @Transactional
    boolean createUser(AppUserDto user);

    boolean updateUser(AppUser user);

    @Transactional
    Optional<AppUser> findUserByLogin(String login);
}

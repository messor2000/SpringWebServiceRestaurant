package epam.project.spring.service.user;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface UserService {
    @Transactional
    boolean createUser(AppUserDto user);

    @Transactional
    Optional<AppUser> findUserByLogin(String login);

    @Transactional
    void createPurse(Purse purse);

    @Transactional
    Purse showUserPurse(AppUserDto user);

    @Transactional
    boolean topUpPurse(int amount, AppUser userDto);

    @Transactional
    @Modifying
    void pay(int price, AppUser user);
}

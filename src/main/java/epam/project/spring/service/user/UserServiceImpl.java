package epam.project.spring.service.user;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.repo.AppUserRepository;
import epam.project.spring.repo.PurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private PurseRepository purseRepository;

    @Override
    @Transactional
    public boolean createUser(AppUserDto userDto) {
        if (userRepository.existsByLogin(userDto.getUsername())) {
            return false;
        }

        Purse purse = new Purse();
        purse.setUser(AppUser.fromDto(userDto));

        purseRepository.save(purse);

        return true;
    }

    @Override
    @Transactional
    public Optional<AppUser> findUserByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    @Transactional
    public void createPurse(Purse purse) {
        purseRepository.save(purse);
    }

    @Override
    public Purse showUserPurse(AppUserDto user) {
        return purseRepository.showPurseAmount(AppUser.fromDto(user));
    }

    @Override
    @Transactional
    public boolean topUpPurse(int amount, AppUser user) {
        if (amount <= 0) {
            return false;
        }

        purseRepository.topUpPurse(amount, user);
        return true;
    }
}

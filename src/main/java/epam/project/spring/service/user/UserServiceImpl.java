package epam.project.spring.service.user;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.PurseDto;
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

        userRepository.save(AppUser.fromDto(userDto));

        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(AppUser user) {
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public Optional<AppUser> findUserByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    @Transactional
    public boolean checkUser(AppUserDto userDto) {
        return userRepository.existsByLogin(userDto.getUsername());
    }

    @Override
    @Transactional
    public void setPurseForUser(AppUserDto userDto) {
        if (!createUser(userDto)) {
            return;
        }

        AppUser user = AppUser.fromDto(userDto);
        PurseDto purseDto = new PurseDto();

        purseDto.setUser(user);
        user.setPurse(Purse.fromDto(purseDto));

        purseRepository.save(Purse.fromDto(purseDto));
    }

    @Override
    @Transactional
    public PurseDto checkPurseAmount(AppUserDto userDto) {

        AppUser user = AppUser.fromDto(userDto);

        return purseRepository.showPurseAmount(user).toDto();
    }

    @Override
    @Transactional
    public void createPurse(Purse purse) {
        purseRepository.save(purse);
    }
}

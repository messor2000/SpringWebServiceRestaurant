package epam.project.spring.service.user;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.repo.AppUserRepository;
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

    @Override
    @Transactional
    public boolean createUser(AppUserDto user) {
        if (userRepository.existsByLogin(user.getUsername())) {
            return false;
        }

        userRepository.save(AppUser.fromDto(user));
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
}

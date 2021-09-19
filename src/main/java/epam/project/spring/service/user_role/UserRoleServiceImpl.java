package epam.project.spring.service.user_role;

import epam.project.spring.entity.UserRole;
import epam.project.spring.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public Optional<UserRole> findUserRoleById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    @Transactional
    public List<UserRole> findAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<UserRole> findUserRoleByName(String role) {
        return userRoleRepository.findByName(role);
    }

    @Override
    @Transactional
    public boolean create(UserRole role) {
        userRoleRepository.save(role);
        return true;
    }
}

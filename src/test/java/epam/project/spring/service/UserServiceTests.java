package epam.project.spring.service;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.UserRole;
import epam.project.spring.repo.AppUserRepository;
import epam.project.spring.repo.UserRoleRepository;
import epam.project.spring.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Aleksandr Ovcharenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTests {
    @MockBean
    private AppUserRepository userRepository;
    @MockBean
    private UserRoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        when(this.userRepository.existsByLogin(any())).thenReturn(true);

        AppUserDto userDto = new AppUserDto();
        userDto.setUsername("username");

        boolean res = userService.createUser(userDto);
        assertFalse(res);
    }

//    @Test
//    void testAddRoleToNewUser() {
//        UserRole roleManager = roleRepository.findByName("MANAGER").get();
//
//        AppUserDto user = new AppUserDto();
//
//        user.setUsername("testUser");
//        user.setEmail("testemail");
//        user.setPassword("password");
//        user.setRole(roleManager);
//
//        userService.createUser(user);
//
//        assertThat(user.getRole().size()).isEqualTo(1);
//    }

//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        user.setEmail("ravikumar@gmail.com");
//        user.setPassword("ravi2020");
//        user.setFirstName("Ravi");
//        user.setLastName("Kumar");
//
//        User savedUser = userRepo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
}

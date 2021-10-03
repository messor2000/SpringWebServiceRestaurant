package epam.project.spring.service;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.repo.AppUserRepository;
import epam.project.spring.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        when(this.userRepository.existsByLogin(any())).thenReturn(true);

        AppUserDto userDto = new AppUserDto();
        userDto.setUsername("username");

        Purse purse = new Purse();
        purse.setUser(AppUser.fromDto(userDto));

        boolean res = userService.createUser(userDto);
        assertFalse(res);
    }
}

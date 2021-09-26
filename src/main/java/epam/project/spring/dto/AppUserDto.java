package epam.project.spring.dto;

import epam.project.spring.entity.Purse;
import epam.project.spring.entity.Role;
import epam.project.spring.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto implements Serializable {

    private Long id;

//    @NotBlank
    private String username;

    @Email
    private String email;

//    @Size(min = 8, max = 20)
    private String password;

//    @NotBlank
////    @Size(min = 8, max = 20)
//    private String passwordRepeat;

//    private Set<UserRole> role;

    private String role;

    private Purse purse;

    public static AppUserDto of(Long id, String username, String password, String email, String role, Purse purse) {
        return new AppUserDto(id, username, password, email, role, purse);
    }
}

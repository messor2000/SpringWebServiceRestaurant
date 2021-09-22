package epam.project.spring.dto;

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

    @NotBlank
//    @Size(min = 8, max = 20)
    private String password;

    @Email
    private String email;

    private Set<UserRole> role;

    public void setRole(UserRole role) {
        this.role.add(role);
    }

    public Set<UserRole> getRole() {
        return role;
    }

    public static AppUserDto of(Long id, String username, String password, String email, Set<UserRole> role) {
        return new AppUserDto(id, username, password, email, role);
    }
}

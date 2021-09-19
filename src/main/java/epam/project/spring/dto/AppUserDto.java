package epam.project.spring.dto;

import epam.project.spring.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private UserRole role;

    public static AppUserDto of(Long id, String username, String password, UserRole role) {
        return new AppUserDto(id, username, password, role);
    }
}

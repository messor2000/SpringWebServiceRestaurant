package epam.project.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto implements Serializable {

    private Long id;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private String role;

    public static AppUserDto of(Long id, String username, String password, String email, String role) {
        return new AppUserDto(id, username, password, email, role);
    }
}

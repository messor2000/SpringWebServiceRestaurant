package epam.project.spring.dto;

import epam.project.spring.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurseDto {

    private Long id;

    @NotBlank
    @Min(value = 0)
    private int amount;

    private AppUser user;

    public static PurseDto of(Long id, int amount, AppUser user) {
        return new PurseDto(id, amount, user);
    }
}

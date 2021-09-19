package epam.project.spring.dto;

import epam.project.spring.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurseDto {

    private Long id;

    @Min(value = 0)
    private int amount;

    private AppUser user;

    public static PurseDto of(Long id, int amount, AppUser user) {
        return new PurseDto(id, amount, user);
    }
}

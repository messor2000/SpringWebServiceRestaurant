package epam.project.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private Long id;

    @NotBlank
    private String name;

    private int price;

    private String category;

    private int amount;

    public static DishDto of(Long id, String name, int price, String category, int amount) {
        return new DishDto(id, name, price, category, amount);
    }
}

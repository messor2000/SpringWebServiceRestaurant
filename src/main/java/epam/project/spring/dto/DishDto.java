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
    private String dishName;

    @NotBlank
    private int price;

    @NotBlank
    private String category;

    @NotBlank
    private int amount;

    public static DishDto of(Long id, String dishName, int price, String category, int amount) {
        return new DishDto(id, dishName, price, category, amount);
    }
}

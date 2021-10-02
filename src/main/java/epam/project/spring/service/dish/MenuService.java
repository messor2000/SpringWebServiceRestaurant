package epam.project.spring.service.dish;

import epam.project.spring.dto.DishDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface MenuService {
    @Transactional
    boolean addDish(DishDto dto);

    @Transactional
    List<DishDto> findDishByName(String name);

    @Transactional
    List<DishDto> showAllMenu();

    @Transactional
    List<DishDto> showAllMenuFromLowPriceToHigh();

    @Transactional
    List<DishDto> showAllMenuFromHighPriceToLow();

    @Transactional
    List<DishDto> showDishesByCategory(String category);
}

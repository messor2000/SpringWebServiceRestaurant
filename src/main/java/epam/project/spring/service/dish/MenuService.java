package epam.project.spring.service.dish;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Dish;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface MenuService {
    @Transactional
    boolean addDish(DishDto dto);

    @Transactional
    Optional<Dish> findDishById(Long id);

//    @Transactional
//    Optional<Dish> findDishByName(String name);

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

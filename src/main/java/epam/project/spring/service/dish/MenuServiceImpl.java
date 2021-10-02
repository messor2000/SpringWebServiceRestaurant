package epam.project.spring.service.dish;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.Dish;
import epam.project.spring.repo.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private DishRepository dishRepository;

    @Override
    @Transactional
    public boolean addDish(DishDto dto) {

        if (dishRepository.existsByName(dto.getName())) {
            return false;
        }

        dishRepository.save(Dish.fromDto(dto));
        return true;
    }

    @Override
    @Transactional
    public List<DishDto> findDishByName(String name) {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findByName(name);

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }

    @Override
    @Transactional
    public List<DishDto> showAllMenu() {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAll();

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }

    @Override
    @Transactional
    public List<DishDto> showAllMenuFromLowPriceToHigh() {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findByOrderByPriceAsc();

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }

    @Override
    @Transactional
    public List<DishDto> showAllMenuFromHighPriceToLow() {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findByOrderByPriceDesc();

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }

    @Override
    public List<DishDto> showDishesByCategory(String category) {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAllByCategoryContains(category);

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }
}

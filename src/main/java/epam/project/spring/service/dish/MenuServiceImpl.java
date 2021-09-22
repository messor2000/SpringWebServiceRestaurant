package epam.project.spring.service.dish;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.Dish;
import epam.project.spring.repo.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return false;
    }

    @Override
    @Transactional
    public Optional<Dish> findDishById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Dish> findDishByName(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<DishDto> showAllMenu() {
        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAll();

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }
}

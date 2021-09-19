package epam.project.spring.repo;

import epam.project.spring.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aleksandr Ovcharenko
 */
public interface DishRepository extends JpaRepository<Dish, Long> {
}

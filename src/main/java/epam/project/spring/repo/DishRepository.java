package epam.project.spring.repo;

import epam.project.spring.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}

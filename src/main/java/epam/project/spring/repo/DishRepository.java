package epam.project.spring.repo;

import epam.project.spring.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
//    @Query("select d from Dish d where d.dishName = :dishName")
//    Optional<Dish> findByName(@Param("dishName") String dishName);

    @Query("select d from Dish d where d.dishName = :dishName")
    List<Dish> findByName(@Param("dishName") String dishName);

//    Query("select d from Dish d where d.dishName = :dishName")
    List<Dish> findAllByCategoryContains(String category);

//    Query("select d from Dish d where d.dishName = :dishName")
//    List<Dish> findByName(@Param("dishName") String dishName);
//
//    Query("select d from Dish d where d.dishName = :dishName")
//    List<Dish> findByName(@Param("dishName") String dishName);

    List<Dish> findByOrderByPriceAsc();

    List<Dish> findByOrderByPriceDesc();
}

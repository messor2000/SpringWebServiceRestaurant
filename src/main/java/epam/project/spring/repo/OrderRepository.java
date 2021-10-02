package epam.project.spring.repo;

import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Dish;
import epam.project.spring.entity.order.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("select o from Order o where o.user = :user")
    Order getByUser(@Param("user") AppUser user);

    @Query("select o from Order o where o.user = :user and o.status= :status")
    Order getByStatusUserOrder(@Param("user") AppUser user, @Param("status") String status);

    List<Order> getAllByUser(AppUser user);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false " +
            "END FROM Order o WHERE o.user = :user")
    boolean existsByUser(@Param("user") AppUser user);

    @Query("select d from Dish d join d.orders o where o.user= :user")
    List<Dish> findDishInUserOrder(@Param("user") AppUser user);

    @Modifying
    @Query(value = "update user_order o set o.status=:status where o.id = :id", nativeQuery = true)
    void changeOrderStatus(@Param("status") String status, @Param("id") Order order);
}

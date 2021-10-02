package epam.project.spring.repo;

import epam.project.spring.entity.order.Order;
import epam.project.spring.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    @Query("SELECT o FROM OrderStatus o where o.status = :status")
    Optional<OrderStatus> findByName(@Param("status") String status);

    @Query("select o from OrderStatus o where o.order = :order")
    OrderStatus showOrderStatus(@Param("order") Order order);

    @Modifying
    @Query(value = "update OrderStatus o set o.status = :status where o.order = :order")
    void setStatusForOrder(@Param("status") String status, @Param("order") Order order);
}

package epam.project.spring.service.order_status;

import epam.project.spring.entity.order.Order;
import epam.project.spring.entity.OrderStatus;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderStatusService {
    Optional<OrderStatus> findOrderStatusByName(String status);

    boolean create(OrderStatus role);

    OrderStatus findOrderStatusByOrderId(Order order);

    @Modifying
    void setStatusForOrder(String status, Order order);
}

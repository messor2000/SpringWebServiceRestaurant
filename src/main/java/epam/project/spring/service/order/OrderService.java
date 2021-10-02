package epam.project.spring.service.order;

import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.order.Order;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderService {
    @Transactional
    boolean createAnOrder(OrderDto orderDto, AppUser user);

    @Modifying
    @Transactional
    void putDishInOrder(String dishName, OrderDto dto);

    @Transactional
    Order findUserOrder(AppUser user);

    @Transactional
    Order findUserOrderByStatus(AppUser user, String status);

    @Transactional
    List<Order> getAllByUser(AppUser user);

    @Transactional
    List<DishDto> showDishInUserOrder(AppUser user);

    @Transactional
    Iterable<OrderDto> showAllOrder();

    @Transactional
    int totalOrderPrice(AppUser user);

    @Modifying
    @Transactional
    void changeOrderStatus(String status, Order order);

    @Modifying
    @Transactional
    void approveOrder(Order order);

    @Transactional
    Optional<Order> findOrderById(Long id);
}

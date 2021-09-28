package epam.project.spring.service.order;

import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Order;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

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
    List<DishDto> showDishInUserOrder(AppUser user);

    @Transactional
    List<OrderDto> showAllOrder();
}

package epam.project.spring.service.order;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Dish;
import epam.project.spring.entity.Order;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderService {

    Order createAnOrder(OrderDto orderDto);

    void putDishInOrder(String dishName, OrderDto dto);

//    void putDishInOrder(DishDto dishDto, OrderDto dto);

    @Transactional
    Order findUserOrder(AppUser user);

    List<DishDto> showDishInOrder(OrderDto orderDto);

    List<OrderDto> showAllOrder();
}

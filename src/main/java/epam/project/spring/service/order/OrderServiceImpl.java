package epam.project.spring.service.order;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Dish;
import epam.project.spring.entity.Order;
import epam.project.spring.repo.DishRepository;
import epam.project.spring.repo.OrderRepository;
import epam.project.spring.service.dish.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public Order createAnOrder(OrderDto orderDto) {
        if (orderRepository.existsById(orderDto.getId())) {
            return Order.fromDto(orderDto);
        }

        return orderRepository.save(Order.fromDto(orderDto));
    }

    @Override
    public void putDishInOrder(String dishName, OrderDto orderDto) {
        List<Dish> dishes = dishRepository.findByName(dishName);
        Dish dish = dishes.get(0);

        Order order = Order.fromDto(orderDto);

        order.getDishes().add(dish);
        dish.getOrders().add(order);

        orderRepository.save(order);
    }

//    @Override
//    public void putDishInOrder(DishDto dishDto,  OrderDto orderDto) {
//        List<Dish> dishes = dishRepository.findByName(dishDto.getName());
//        Dish dish = dishes.get(0);
//
//        Order order = Order.fromDto(orderDto);
//
//        order.getDishes().add(dish);
//        dish.getOrders().add(order);
//
//        orderRepository.save(order);
//    }

    @Override
    public Order findUserOrder(AppUser user) {
        if (orderRepository.getByUser(user) == null) {
            return
        }

        return orderRepository.getByUser(user);
    }

    @Override
    public List<DishDto> showDishInOrder(OrderDto orderDto) {

        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAll();

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
//        menuService.
    }

    @Override
    public List<OrderDto> showAllOrder() {
        final List<OrderDto> userOrders = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        orders.forEach(x -> userOrders.add(x.toDto()));

        return userOrders;
    }


}

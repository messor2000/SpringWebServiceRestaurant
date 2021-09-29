package epam.project.spring.service.order;

import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Dish;
import epam.project.spring.entity.Order;
import epam.project.spring.repo.DishRepository;
import epam.project.spring.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    @Transactional
    public boolean createAnOrder(OrderDto orderDto, AppUser user) {
        if (orderRepository.existsByUser(user)) {
             return false;
        }

        orderRepository.save(Order.fromDto(orderDto));
        return true;
    }

    @Override
    @Transactional
    public void putDishInOrder(String dishName, OrderDto orderDto) {
        List<Dish> dishes = dishRepository.findByName(dishName);
        Dish dish = dishes.get(0);

        Order order = Order.fromDto(orderDto);

        dish.getOrders().add(order);

        dishRepository.save(dish);
    }

    @Override
    @Transactional
    public Order findUserOrder(AppUser user) {
        return orderRepository.getByUser(user);
    }

    @Override
    @Transactional
    public List<DishDto> showDishInUserOrder(AppUser user) {

        final List<DishDto> menu = new ArrayList<>();
        List<Dish> dishes = orderRepository.findDishInUserOrder(user);

        dishes.forEach(x -> menu.add(x.toDto()));
        return menu;
    }

    @Override
    @Transactional
    public Iterable<OrderDto> showAllOrder() {
        final List<OrderDto> userOrders = new ArrayList<>();

        Iterable<Order> orders = orderRepository.findAll();

        orders.forEach(x -> userOrders.add(x.toDto()));

        return userOrders;
    }
}

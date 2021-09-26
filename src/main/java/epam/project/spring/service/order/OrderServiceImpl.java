package epam.project.spring.service.order;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.entity.Dish;
import epam.project.spring.entity.Order;
import epam.project.spring.repo.OrderRepository;
import epam.project.spring.service.dish.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Order createAnOrder(OrderDto orderDto) {
        if (orderRepository.existsById(orderDto.getId())) {
            return Order.fromDto(orderDto);
        }

        return orderRepository.save(Order.fromDto(orderDto));
    }

    @Override
    public void putDishInOrder(DishDto dishDto, AppUserDto appUserDto) {

    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Dish> showDishInOrder(OrderDto orderDto) {
//        menuService.
        return null;
    }


}

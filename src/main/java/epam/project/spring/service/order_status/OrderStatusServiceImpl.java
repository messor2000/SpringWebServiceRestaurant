package epam.project.spring.service.order_status;

import epam.project.spring.entity.order.Order;
import epam.project.spring.entity.OrderStatus;
import epam.project.spring.repo.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService{

    @Autowired
    OrderStatusRepository statusRepository;

    @Override
    @Transactional
    public Optional<OrderStatus> findOrderStatusByName(String status) {
        return statusRepository.findByName(status);
    }

    @Override
    @Transactional
    public boolean create(OrderStatus status) {
        return false;
    }

    @Override
    @Transactional
    public OrderStatus findOrderStatusByOrderId(Order order) {
        return statusRepository.showOrderStatus(order);
    }

    @Override
    @Transactional
    public void setStatusForOrder(String status, Order order) {
        statusRepository.setStatusForOrder(status, order);
    }
}

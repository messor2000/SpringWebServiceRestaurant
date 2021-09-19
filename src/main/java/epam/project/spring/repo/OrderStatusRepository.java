package epam.project.spring.repo;

import epam.project.spring.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}

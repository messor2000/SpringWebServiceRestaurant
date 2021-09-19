package epam.project.spring.repo;

import epam.project.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}

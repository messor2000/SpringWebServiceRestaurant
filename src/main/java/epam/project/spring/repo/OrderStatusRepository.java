package epam.project.spring.repo;

import epam.project.spring.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}

package epam.project.spring.repo;

import epam.project.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

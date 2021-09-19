package epam.project.spring.repo;

import epam.project.spring.entity.Purse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aleksandr Ovcharenko
 */
public interface PurseRepository extends JpaRepository<Purse, Long> {
}

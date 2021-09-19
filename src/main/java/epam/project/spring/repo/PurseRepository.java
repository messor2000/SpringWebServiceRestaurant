package epam.project.spring.repo;

import epam.project.spring.entity.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface PurseRepository extends JpaRepository<Purse, Long> {
}

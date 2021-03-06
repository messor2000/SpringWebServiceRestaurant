package epam.project.spring.service.purse;

import epam.project.spring.entity.Purse;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface PurseService {
    Optional<Purse> findPurseByUser(String role);
}

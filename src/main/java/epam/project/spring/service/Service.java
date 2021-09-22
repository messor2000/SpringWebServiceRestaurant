package epam.project.spring.service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
public interface Service<T> {

    Optional<T> getById(int id);

    List<T> getAll();

}

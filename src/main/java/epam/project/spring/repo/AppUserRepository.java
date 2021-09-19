package epam.project.spring.repo;

import epam.project.spring.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT u FROM AppUser u where u.username = :username")
    AppUser findByLogin(@Param("username") String username);
}

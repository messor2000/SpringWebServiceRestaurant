package epam.project.spring.repo;

import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface PurseRepository extends JpaRepository<Purse, Long> {

    @Query("select p from Purse p where p.user = :user")
    Purse showPurseAmount(@Param("user") AppUser user);

    @Modifying
    @Query(value = "update purse p set p.amount=amount+ :amount where p.user_id = :user_id", nativeQuery = true)
    void topUpPurse(@Param("amount") int amount, @Param("user_id") AppUser user);
}

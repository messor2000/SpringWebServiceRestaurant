package epam.project.spring.repo;

import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksandr Ovcharenko
 */
@Repository
public interface PurseRepository extends JpaRepository<Purse, Long> {

    @Query("select p from Purse p where p.user = :user")
    Purse showPurseAmount(@Param("user") AppUser user);

    @Query("update Purse set amount=amount + :amount where user = :user")
    void topUpPurse(@Param("amount") int amount, @Param("user") AppUser user);
}

//"update purse set amount=amount+? where user_id=?"
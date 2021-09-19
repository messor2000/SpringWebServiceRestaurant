package epam.project.spring.entity;

import epam.project.spring.dto.PurseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "purse")
public class Purse implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "amount")
    private int amount;

    public Purse(AppUser user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    public static Purse of(Long id, AppUser user, int amount) {
        return Purse.builder()
                .id(id)
                .user(user)
                .amount(amount)
                .build();
    }

    public static Purse fromDto(PurseDto purseDto) {
        return Purse.of(purseDto.getId(), purseDto.getUser(), purseDto.getAmount());
    }

    public PurseDto toDto() {
        return PurseDto.of(id, amount, user);
    }

    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
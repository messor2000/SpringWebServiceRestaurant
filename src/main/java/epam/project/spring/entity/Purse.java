package epam.project.spring.entity;

import epam.project.spring.dto.PurseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "purse")
public class Purse implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
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

package epam.project.spring.entity;

import epam.project.spring.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "user_order")
public class Order implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Set<OrderStatus> status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

        @ManyToMany(fetch = FetchType.EAGER,mappedBy = "orders")
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "dishes_orders",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, updatable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "dish_name", referencedColumnName = "dish_name", nullable = false, updatable = false)})
    private Set<Dish> dishes = new HashSet<>();

    public Order(Set<OrderStatus> status, AppUser user, Date creationDate, Date updateDate, Set<Dish> dishes) {
        this.status = status;
        this.user = user;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.dishes = dishes;
    }

    public static Order of(Long id, AppUser user, Set<OrderStatus> status, Date creationDate, Date updateDate) {
        return Order.builder()
                .id(id)
                .status(status)
                .user(user)
                .creationDate(creationDate)
                .updateDate(updateDate)
                .build();
    }

    public static Order fromDto(OrderDto orderDto) {
        return Order.of(orderDto.getId(), orderDto.getUser(), orderDto.getStatus(),
                orderDto.getCreationDate(), orderDto.getUpdateDate());
    }

    public OrderDto toDto() {
        return OrderDto.of(id, status, user, creationDate, updateDate);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

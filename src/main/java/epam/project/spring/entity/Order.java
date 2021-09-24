package epam.project.spring.entity;

import epam.project.spring.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Builder(toBuilder = true)
@Table(name = "order")
public class Order implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderStatus> status;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private AppUser user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "dishes_orders",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id",
                    nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "dish_name", referencedColumnName = "dish_name",
                            nullable = false, updatable = false)})
    private Set<Dish> dishes = new HashSet<>();

    public Order(Set<OrderStatus> status, Date creationDate, Date updateDate, Set<Dish> dishes) {
        this.status = status;
//        this.user = user;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.dishes = dishes;
    }

    public static Order of(Long id, Set<OrderStatus> status, Date creationDate, Date updateDate)  {
        return Order.builder()
                .id(id)
                .status(status)
//                .user(user)
                .creationDate(creationDate)
                .updateDate(updateDate)
                .build();
    }

//    public Order fromDto(OrderDto orderDto) {
//        return Order.of(orderDto.getId(), orderDto.getStatus(), orderDto.getUser(),
//                orderDto.getCreationDate(), orderDto.getUpdateDate());
//    }

    public Order fromDto(OrderDto orderDto) {
        return Order.of(orderDto.getId(), orderDto.getStatus(),
                orderDto.getCreationDate(), orderDto.getUpdateDate());
    }

    public OrderDto toDto() {
        return OrderDto.of(id, status, creationDate, updateDate);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

package epam.project.spring.entity;

import epam.project.spring.dto.DishDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "dish")
public class Dish implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "price")
    private int price;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    private int amount;

//    @ManyToMany(mappedBy = "order", fetch = FetchType.LAZY)
//    private Set<Order> orders = new HashSet<>();

//    public Dish(String dishName, int price, String category, int amount, Set<Order> orders) {
//        this.dishName = dishName;
//        this.price = price;
//        this.category = category;
//        this.amount = amount;
//        this.orders = orders;
//    }

    public Dish(String dishName, int price, String category, int amount) {
        this.dishName = dishName;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public static Dish of(Long id, String dishName, int price, String category, int amount) {
        return Dish.builder()
                .id(id)
                .dishName(dishName)
                .price(price)
                .category(category)
                .amount(amount)
                .build();
    }

    public static Dish fromDto(DishDto dishDto) {
        return Dish.of(dishDto.getId(), dishDto.getName(), dishDto.getPrice(), dishDto.getCategory(),
                dishDto.getAmount());
    }

    public DishDto toDto() {
        return DishDto.of(id, dishName, price, category, amount);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

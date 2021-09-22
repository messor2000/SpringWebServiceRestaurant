package epam.project.spring.dto;

import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Set<OrderStatus> status;

    private AppUser user;

    private Date creationDate;

    private Date updateDate;

    public static OrderDto of(Long id, Set<OrderStatus> status, AppUser user, Date creationDate, Date updateDate) {
        return new OrderDto(id, status, user, creationDate, updateDate);
    }
}

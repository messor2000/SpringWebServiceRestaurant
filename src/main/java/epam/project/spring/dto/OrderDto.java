package epam.project.spring.dto;

import epam.project.spring.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private Long id;

    private String status;

    private AppUser user;

    private Date creationDate;

    private Date updateDate;

    public static OrderDto of(Long id, String status, AppUser user, Date creationDate, Date updateDate) {
        return new OrderDto(id, status, user,creationDate, updateDate);
    }
}

package epam.project.spring.entity;

import epam.project.spring.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "app_user")
public class AppUser implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String role;

    public static AppUser of(Long id, String login, String password, String role) {
        return AppUser.builder()
                .id(id)
                .username(login)
                .password(password)
                .role(role)
                .build();
    }

    public static AppUser fromDto(AppUserDto userDto) {
        return AppUser.of(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }

    public AppUserDto toDto() {
        return AppUserDto.of(id, username, null, email, role);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setUserPurse(Purse purse) {
        purse.setUser(this);
    }
}

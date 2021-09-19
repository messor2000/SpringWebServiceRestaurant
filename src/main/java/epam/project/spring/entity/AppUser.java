package epam.project.spring.entity;

import epam.project.spring.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "app_user")
public class AppUser implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "role", nullable = false)
    private UserRole role;

    public AppUser(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUser(String username, String password, Set<GrantedAuthority> roles) {
    }

    public static AppUser of(Long id, String login, String password, UserRole role) {
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
        return AppUserDto.of(id, username, null, role);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

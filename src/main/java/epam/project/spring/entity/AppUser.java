package epam.project.spring.entity;

import epam.project.spring.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserRole> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Purse purse;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

//    public AppUser(String username, String password, Set<UserRole> role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }

    public AppUser(String username, String password, Set<GrantedAuthority> roles) {
    }

    public static AppUser of(Long id, String login, String password, Set<UserRole> roles) {
        return AppUser.builder()
                .id(id)
                .username(login)
                .password(password)
                .roles(roles)
                .build();
    }

    public static AppUser fromDto(AppUserDto userDto) {
        return AppUser.of(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }

    public AppUserDto toDto() {
        return AppUserDto.of(id, username, null, email, roles);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

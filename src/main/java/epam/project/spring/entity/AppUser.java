package epam.project.spring.entity;

import epam.project.spring.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

//    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<UserRole> roles;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
//    private Purse purse;

//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private Set<Order> order;

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

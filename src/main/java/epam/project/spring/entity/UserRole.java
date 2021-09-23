package epam.project.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
    private Set<AppUser> users;

    public UserRole(@NonNull String role) {
        this.role = role;
    }

    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

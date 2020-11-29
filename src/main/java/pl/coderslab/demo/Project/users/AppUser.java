package pl.coderslab.demo.Project.users;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.userdetails.UserDetails;
import pl.coderslab.demo.Project.school.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    @NotEmpty(message = "Pole nie może być puste")
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<SchoolClass> schoolClasses;

}

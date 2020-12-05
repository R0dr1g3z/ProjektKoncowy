package pl.coderslab.demo.Project.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.demo.Project.users.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToOne
    private AppUser director;
    @OneToMany
    private List<AppUser> teachers;
    @OneToMany
    private List<AppUser> students;
    @OneToMany
    private List<SchoolClass> schoolClasses;
}

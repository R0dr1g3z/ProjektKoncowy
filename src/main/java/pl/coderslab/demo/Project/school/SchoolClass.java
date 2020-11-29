package pl.coderslab.demo.Project.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.demo.Project.users.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String educator;
}

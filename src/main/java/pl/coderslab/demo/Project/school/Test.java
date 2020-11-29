package pl.coderslab.demo.Project.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.demo.Project.users.AppUser;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String schoolSubject;
    private LocalDate date;
    private int rating;
    @ManyToOne
    private AppUser appUser;
}


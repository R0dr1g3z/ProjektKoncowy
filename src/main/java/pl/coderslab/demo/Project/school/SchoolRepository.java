package pl.coderslab.demo.Project.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.demo.Project.users.AppUser;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {
    School findByDirector(AppUser appUser);
}

package pl.coderslab.demo.Project.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.demo.Project.users.AppUser;

import java.util.List;

@Repository
public interface ShortTestRepository extends JpaRepository<ShortTest,Long> {
    List<ShortTest> findAllByAppUser(AppUser appUser);
    List<ShortTest> findAllByAppUserAndSchoolSubject(AppUser appUser,String schoolSubject);
}

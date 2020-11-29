package pl.coderslab.demo.Project.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.demo.Project.school.SchoolClass;
import pl.coderslab.demo.Project.school.Test;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    List<AppUser> findAllByRolesIsLike(String role);

    List<AppUser> findAllByRoles(Role role);

    AppUser findById(int id);

    AppUser deleteAppUserById(Long id);

    List<AppUser> findAllBySchoolClasses(SchoolClass schoolClass);



    @Modifying
    @Query(value = "DELETE FROM EndProject2.user_roles WHERE app_user_id = ?1", nativeQuery = true)
    void deleteUserRole(Long id);

    @Modifying
    @Query(value = "DELETE FROM EndProject2.user_school_classes WHERE app_user_id = ?1", nativeQuery = true)
    void deleteUserSchoolClass(Long id);
}


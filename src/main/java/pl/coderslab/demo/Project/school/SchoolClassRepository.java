package pl.coderslab.demo.Project.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    SchoolClass findByName(String name);
    List<SchoolClass> findAllByName(String name);

    @Modifying
    @Query(value = "DELETE FROM user_school_classes WHERE school_classes_id = ?1",nativeQuery = true)
    void deleteAllStudentFromSchoolClass(Long id);
    @Modifying
    @Query(value = "DELETE FROM school_school_classes WHERE school_classes_id = ?1",nativeQuery = true)
    void deleteSchoolClassSchool(Long id);
}

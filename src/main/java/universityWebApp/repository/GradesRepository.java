package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Grades;


import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

    @Query("select a.grade from Grades a where a.studentId = ?1")
    List<Integer> findByStudentID(String student_id);

    @Query("select a.grade from Grades a where a.moduleId = ?1")
    List<Integer> findByModuleID(Long module_id);

    @Query("select a.grade from Grades a where a.moduleId =?1 and a.studentId = ?2")
    Grades findByModuleAndStudentID(Long module_id, String student_id);
}

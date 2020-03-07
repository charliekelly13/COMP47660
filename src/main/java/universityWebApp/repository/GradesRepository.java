package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Grade;


import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grade, Long> {

    @Query("select a.grade from Grade a where a.studentId = ?1")
    List<Integer> findByStudentID(String student_id);

    @Query("select a.grade from Grade a where a.moduleId = ?1")
    List<Integer> findByModuleID(Long module_id);

    @Query("select a from Grade a where a.moduleId =?1 and a.studentId = ?2")
    Grade findByModuleAndStudentID(Long module_id, String student_id);
}

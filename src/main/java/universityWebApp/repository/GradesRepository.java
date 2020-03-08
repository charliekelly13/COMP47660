package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Grade;
import universityWebApp.model.GradeId;


import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grade, GradeId> {

    @Query("select a.grade from Grade a where a.studentId = ?1")
    List<Integer> findByStudentID(String studentId);

    @Query("select a from Grade a where a.moduleId = ?1")
    List<Grade> findGradesByModuleId(long moduleId);

    @Query("select a from Grade a where a.moduleId =?1 and a.studentId = ?2")
    Grade findByModuleAndStudentID(long moduleId, String studentId);
}

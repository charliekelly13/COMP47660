package universityWebApp.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradesRepository {

    @Query("select a.grade from Grades a where a.student_Id = ?1")
    List<String> findByStudentID(String student_id);

    @Query("select a.grade from Grades a where a.module_Id = ?1")
    List<String> findByModuleID(String module_id);

    @Query("select a.grade from Grades a where a.module_Id =?1 && a.student_id = ?2")
    List<String> findByModuleAndStudentID(String module_id, String student_id);
}

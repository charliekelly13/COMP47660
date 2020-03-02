package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Enrollment;
import universityWebApp.model.EnrollmentId;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    //TODO
    //@Query("select a.id_author from Authorship a where a.id_book = ?1")
    //List<Long> findAuthorByBookId(Long book_id) throws AuthorNotFoundException;
}
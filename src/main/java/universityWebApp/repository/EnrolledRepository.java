package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Enrolled;
import universityWebApp.model.EnrolledId;
import universityWebApp.model.Student;

import java.util.List;

@Repository
public interface EnrolledRepository extends JpaRepository<Enrolled, EnrolledId> {


    //TODO
    //@Query("select a.id_author from Authorship a where a.id_book = ?1")
    //List<Long> findAuthorByBookId(Long book_id) throws AuthorNotFoundException;

}
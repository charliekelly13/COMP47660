package universityWebApp.exception;


public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String student_id) {
        super(String.format("No Student found with id : '%s'", student_id));
    }
}

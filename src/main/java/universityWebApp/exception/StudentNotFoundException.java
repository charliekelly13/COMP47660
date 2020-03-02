package universityWebApp.exception;


public class StudentNotFoundException extends Exception {
    private String student_id;
    public StudentNotFoundException(String student_id) {
        super(String.format("No Student found with id : '%s'", student_id));
    }
}

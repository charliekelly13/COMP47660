package universityWebApp.exception;


public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String studentId) {
        super(String.format("No Student found with id : '%s'", studentId));
    }
}

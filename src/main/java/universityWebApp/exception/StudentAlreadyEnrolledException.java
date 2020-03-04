package universityWebApp.exception;

public class StudentAlreadyEnrolledException extends Exception {
    public StudentAlreadyEnrolledException(String studentId, long moduleId) {
        super("Student with id " + studentId + " is already enrolled in module with id " + moduleId);
    }
}

package universityWebApp.exception;

public class FeesNotPaidException extends Exception {

    public FeesNotPaidException() {
        super("Student must pay fees before enrolling in a module.");
}
}

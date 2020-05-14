package universityWebApp.exception;

public class IPNotFoundException extends Exception {

    public IPNotFoundException() {
        super("This IP has not been used before.");
    }
}

package universityWebApp.exception;

public class IpNotFoundException extends Exception {

    public IpNotFoundException() {
        super("This IP has not been used before.");
    }
}

package universityWebApp.exception;

public class ModuleTerminatedException extends Exception {

    public ModuleTerminatedException() {
        super("You cannot enrol into a terminated module.");
    }
}

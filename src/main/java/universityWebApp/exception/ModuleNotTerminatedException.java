package universityWebApp.exception;

public class ModuleNotTerminatedException extends Exception {

    public ModuleNotTerminatedException() {
        super("A module must be terminated for grades to be added to it.");
    }
}

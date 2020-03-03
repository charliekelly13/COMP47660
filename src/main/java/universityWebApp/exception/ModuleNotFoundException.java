package universityWebApp.exception;


public class ModuleNotFoundException extends Exception {

    public ModuleNotFoundException(long module_id) {
        super(String.format("No Module found with id : '%s'", module_id));
    }
}

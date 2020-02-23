package universityWebApp.exception;


public class ModuleNotFoundException extends Exception {
    private long module_id;
    public ModuleNotFoundException(Long module_id) {
        super(String.format("No Module found with id : '%s'", module_id));
    }
}

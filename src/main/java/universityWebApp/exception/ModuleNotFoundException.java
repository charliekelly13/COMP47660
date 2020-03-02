package universityWebApp.exception;


public class ModuleNotFoundException extends Exception {
    private String module_id;
    public ModuleNotFoundException(String module_id) {
        super(String.format("No Module found with id : '%s'", module_id));
    }
}

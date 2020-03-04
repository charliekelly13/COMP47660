package universityWebApp.exception;

public class ModuleFullException extends Exception {

    public ModuleFullException(long moduleId) {
        super("Enrolment failed: Module " + moduleId + " has no available spaces.");
    }
}

package slovenskoit.zadanie.service.diskSpace;

public class DiskSpaceException extends RuntimeException {

    public DiskSpaceException(String message) {
        super(message);
    }

    public DiskSpaceException(String message, Throwable cause) {
        super(message, cause);
    }

}

package slovenskoit.zadanie.service.device;

public class DeviceServiceException extends RuntimeException {

    public DeviceServiceException(String message) {
        super(message);
    }

    public DeviceServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

package slovenskoit.zadanie.service.device;

import slovenskoit.zadanie.entity.Device;

public interface DeviceService {
    void startStream(Device device) throws DeviceServiceException;
    void stopStream(Device device) throws DeviceServiceException;
}

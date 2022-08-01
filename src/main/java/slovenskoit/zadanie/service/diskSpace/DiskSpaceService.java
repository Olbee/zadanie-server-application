package slovenskoit.zadanie.service.diskSpace;

import slovenskoit.zadanie.entity.Device;
import slovenskoit.zadanie.entity.DiskSpace;

public interface DiskSpaceService {
    DiskSpace addDeviceToDiskSpace(Device device, Long disk_space_id) throws DiskSpaceException;
    DiskSpace deleteDeviceFromDiskSpace(Device device, Long disk_space_id) throws DiskSpaceException;
    boolean increaseCapacity(DiskSpace diskSpace, int increaseBy) throws DiskSpaceException;

}

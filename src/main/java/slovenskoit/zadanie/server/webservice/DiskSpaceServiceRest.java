package slovenskoit.zadanie.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import slovenskoit.zadanie.entity.Device;
import slovenskoit.zadanie.entity.DiskSpace;
import slovenskoit.zadanie.service.diskSpace.DiskSpaceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/diskSpace")
public class DiskSpaceServiceRest {

    @Autowired
    DiskSpaceService diskSpaceService;

    @PostMapping("/device/add/{disk_space_id}")
    public void addDevice(@RequestBody Device device, @PathVariable Long disk_space_id) {
        diskSpaceService.addDeviceToDiskSpace(device, disk_space_id);
    }

    @PostMapping("/device/delete/{disk_space_id}")
    public void deleteDevice(@RequestBody Device device, @PathVariable Long disk_space_id) {
        diskSpaceService.deleteDeviceFromDiskSpace(device, disk_space_id);
    }

    @PostMapping("/increase/{increaseBy}")
    public void raiseDiskSpace(@RequestBody DiskSpace diskSpace, @PathVariable int increaseBy) {
        diskSpaceService.increaseCapacity(diskSpace, increaseBy);
    }

}

package slovenskoit.zadanie.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import slovenskoit.zadanie.entity.Device;
import slovenskoit.zadanie.entity.DiskSpace;
import slovenskoit.zadanie.service.device.DeviceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/device")
public class DeviceServiceRest {

    @Autowired
    DeviceService deviceService;

    @PostMapping("/startStream")
    public void startDataStream(@RequestBody Device device) {
        deviceService.startStream(device);
    }

    @PostMapping("/stopStream")
    public void stopDataStream(@RequestBody Device device) {
        deviceService.stopStream(device);
    }

}

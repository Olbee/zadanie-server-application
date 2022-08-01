package slovenskoit.zadanie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import slovenskoit.zadanie.entity.Customer;
import slovenskoit.zadanie.entity.Device;
import slovenskoit.zadanie.service.customer.CustomerService;
import slovenskoit.zadanie.service.device.DeviceService;
import slovenskoit.zadanie.service.diskSpace.DiskSpaceService;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    DiskSpaceService diskSpaceService;

    @Autowired
    DeviceService deviceService;


    @Override
    public void run(String... args) {


        Customer customer1 = new Customer("11111@mail.com", "11111  ", "11111", "11111", "11111");
        Customer customer2 = new Customer("22222@mail.com", "22222", "22222", "22222", "22222");

        Device device1 = new Device("device1");
        Device device2 = new Device("device2");

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        diskSpaceService.increaseCapacity(customer1.getDiskSpace(), 15);
        diskSpaceService.increaseCapacity(customer2.getDiskSpace(), 40);

//        deviceService.addDevice(device1);
//        deviceService.addDevice(device2);

        deviceService.startStream(device1);
        deviceService.startStream(device2);
        deviceService.stopStream(device1);




    }
}

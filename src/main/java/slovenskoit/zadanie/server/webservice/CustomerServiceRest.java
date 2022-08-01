package slovenskoit.zadanie.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import slovenskoit.zadanie.entity.Customer;
import slovenskoit.zadanie.service.customer.CustomerService;
import slovenskoit.zadanie.service.diskSpace.DiskSpaceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerServiceRest {

    @Autowired
    CustomerService customerService;

    @Autowired
    DiskSpaceService diskSpaceService;

    @PostMapping("/add")
    public void addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    // 100$
    @PostMapping("/buyDiskSpace")
    public boolean buyDiskSpace(@Valid @RequestBody Customer customer) {
        int PRICE_100GB = 150;
        if (customerService.getMoneyAmount(customer) > PRICE_100GB) {
            diskSpaceService.increaseCapacity(customer.getDiskSpace(), 100);
            customerService.removeMoney(customer, PRICE_100GB);
            return true;
        }
        return false;
    }

    @PostMapping("/addMoney/{sumToAdd}")
    public void deposit(@Valid @RequestBody Customer customer, BindingResult bindingResult, @PathVariable int sumToAdd) {
        customerService.addMoney(customer, sumToAdd);
    }

    @PostMapping("/change/password/{newPassword}")
    public void changePassword(@Valid @RequestBody Customer customer,BindingResult bindingResult, @PathVariable String newPassword) {
        customerService.changePassword(customer, newPassword);
    }

    @PostMapping("/change/nameAndSurname/{newName}/{newSurname}")
    public void changePassword(@Valid @RequestBody Customer customer,
                               BindingResult bindingResult,
                               @PathVariable String newName,
                               @PathVariable String newSurname) {
        customerService.changeNameAndSurname(customer, newName, newSurname);
    }

    @GetMapping("/getAll")
    public void getCostumers() {
        customerService.getCustomers();
    }

}

package service;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import slovenskoit.zadanie.entity.Customer;
import slovenskoit.zadanie.server.Server;
import slovenskoit.zadanie.service.customer.CustomerService;
import slovenskoit.zadanie.service.customer.CustomerServiceJPA;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Server.class)
public class customerServiceTest {

    @Autowired
    private CustomerService customerService = new CustomerServiceJPA();

    @Test
    void addCustomerTest() {
        int customers_size = customerService.getCustomers().size();

        customerService.addCustomer(new Customer("test@gmail.com", "admin", "admin", "tester", "tester"));
        assertEquals(customers_size + 1, customerService.getCustomers().size());
    }

    @Test
    void customerMoneyOperationsTest() {
        Customer customer = new Customer("test@gmail.com", "admin", "admin", "tester", "tester");

        assertEquals(0, customer.getMoneyAmount());

        customerService.addMoney(customer, 300);
        assertEquals(300, customer.getMoneyAmount());

        customerService.removeMoney(customer, 30);
        assertEquals(270, customer.getMoneyAmount());
    }
}

package slovenskoit.zadanie.service.customer;

import slovenskoit.zadanie.entity.Customer;
import slovenskoit.zadanie.service.device.DeviceServiceException;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer) throws CustomerException ;
    void changePassword(Customer customer, String newPassword) throws CustomerException;
    void changeNameAndSurname(Customer customer, String newName, String newSurname) throws CustomerException;

    void addMoney(Customer customer, int sumToAdd) throws CustomerException;
    void removeMoney(Customer customer, int sumToRemove) throws CustomerException;
    int getMoneyAmount(Customer customer) throws CustomerException;
   List<Customer> getCustomers() throws CustomerException;
}

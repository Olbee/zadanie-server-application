package slovenskoit.zadanie.service.customer;


import slovenskoit.zadanie.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerServiceJPA implements CustomerService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        try {
            entityManager.persist(customer);
        } catch (Exception e) {
            throw new CustomerException("Problem adding customer");
        }
    }

    @Override
    public void addMoney(Customer customer, int sumToAdd) throws CustomerException {
        int newMoneyAmount = customer.getMoneyAmount() + sumToAdd;
        customer.setMoneyAmount(newMoneyAmount);
        try {
            entityManager.createNamedQuery("Customer.updateMoneyAmount")
                .setParameter("newMoneyAmount", newMoneyAmount)
                .setParameter("login", customer.getLogin())
                .setParameter("email", customer.getEmail())
                .executeUpdate();
        } catch (RuntimeException e) {
            throw new CustomerException("Problem adding money");
        }
    }

    @Override
    public void removeMoney(Customer customer, int sumToRemove) throws CustomerException {
        int newMoneyAmount = customer.getMoneyAmount() - sumToRemove;
        customer.setMoneyAmount(newMoneyAmount);
        try {
            entityManager.createNamedQuery("Customer.updateMoneyAmount")
                    .setParameter("newMoneyAmount", newMoneyAmount)
                    .setParameter("login", customer.getLogin())
                    .setParameter("email", customer.getEmail())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new CustomerException("Problem removing money");
        }
    }

    @Override
    public int getMoneyAmount(Customer customer) throws CustomerException {
        Object moneyAmount = null;
        try {
            moneyAmount = entityManager.createNamedQuery("Customer.getMoneyAmount")
                    .setParameter("login", customer.getLogin())
                    .setParameter("email", customer.getEmail())
                    .getSingleResult();
        } catch (RuntimeException e) {
            throw new CustomerException("Problem getting money");
        }
        if (moneyAmount == null) return -1;
        else return (int) moneyAmount;
    }

    @Override
    public void changePassword(Customer customer, String newPassword) throws CustomerException {
        try {
            entityManager.createNamedQuery("Customer.changePassword")
                    .setParameter("newPassword", newPassword)
                    .setParameter("email", customer.getEmail())
                    .setParameter("login", customer.getLogin())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new CustomerException("Problem changing password");
        }
    }

    @Override
    public void changeNameAndSurname(Customer customer, String newName, String newSurname) throws CustomerException {
        try {
            entityManager.createNamedQuery("Customer.changeNameAndSurname")
                    .setParameter("newName", newName)
                    .setParameter("newSurname", newSurname)
                    .setParameter("email", customer.getEmail())
                    .setParameter("login", customer.getLogin())
                    .executeUpdate();
        }
         catch (RuntimeException e) {
            throw new CustomerException("Problem changing name and surname");
        }
    }

    @Override
    public List<Customer> getCustomers() throws CustomerException {
        try {
            return entityManager.createNamedQuery("Customer.getCustomers")
                    .getResultList();
        } catch (RuntimeException e) {
            throw new CustomerException("Problem getting customers");
        }

    }

}

package slovenskoit.zadanie.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

//@NamedQuery(name = "Customer.checkCustomerExists", query = "SELECT c.login, c.email FROM Customer c WHERE c.login =: login AND c.email =: email")
@NamedQuery(name = "Customer.updateMoneyAmount", query = "UPDATE Customer c SET c.moneyAmount =: newMoneyAmount WHERE c.login =: login AND c.email =: email")
@NamedQuery(name = "Customer.getMoneyAmount", query = "SELECT c.moneyAmount FROM Customer c WHERE c.login =: login AND c.email =: email")
@NamedQuery(name = "Customer.changePassword", query = "UPDATE Customer c SET c.password =: newPassword WHERE c.email =: email AND c.login =: login")
@NamedQuery(name = "Customer.changeNameAndSurname", query = "UPDATE Customer c SET c.name =: newName, c.surname =: newSurname WHERE c.email =: email AND c.login =: login")
@NamedQuery(name = "Customer.getCustomers", query = "Select c FROM Customer c ORDER BY c.id DESC")

@Entity
@Table(indexes = {
        @Index(name = "customer_id", columnList = "id"),
        @Index(name = "customer_email", columnList = "email"),
        @Index(name = "customer_login", columnList = "login"),
})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Login should not be empty")
    @Size(min = 4, max = 16, message = "Login should be between 4 and 16 characters")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 characters")
    private String password; //SHOULD BE ENCODED

    private String name;
    private String surname;
    private int moneyAmount = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private DiskSpace diskSpace = new DiskSpace();

    public Customer() {

    }

    public Customer(String email, String login, String password, String name, String surname) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DiskSpace getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(DiskSpace diskSpace) {
        this.diskSpace = diskSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return moneyAmount == customer.moneyAmount && Objects.equals(id, customer.id) && Objects.equals(email, customer.email) && Objects.equals(login, customer.login) && Objects.equals(password, customer.password) && Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(diskSpace, customer.diskSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, password, name, surname, moneyAmount, diskSpace);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", diskSpace=" + diskSpace +
                '}';
    }

}

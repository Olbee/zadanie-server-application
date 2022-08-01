package slovenskoit.zadanie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "DiskSpace.updateDiskSpace", query = "UPDATE DiskSpace ds SET ds.size =: newSize WHERE ds.id =: id")
@NamedQuery(name = "DiskSpace.getDiskSpaceById", query = "SELECT ds FROM DiskSpace ds WHERE ds.id =: id")

@Entity
@Table(indexes = @Index(name = "disk_space_id", columnList = "id"))
public class DiskSpace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //default size is 60.
    private int size = 60;

    @OneToOne(mappedBy = "diskSpace", cascade = CascadeType.ALL)
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "diskSpace", cascade = CascadeType.ALL)
    private List<Device> devices;

    public DiskSpace() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        if (devices == null) devices = new ArrayList<>();
        device.setDiskSpace(this);
        this.devices.add(device);
    }

    public void deleteDevice(Device device) {
        device.setDiskSpace(null);
        this.devices.remove(device);
    }

    @Override
    public String toString() {
        return "DiskSpace{" +
                "id=" + id +
                ", size=" + size +
                ", customer=" + customer +
                ", devices=" + devices +
                '}';
    }

}

package slovenskoit.zadanie.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Device.deleteDevice", query = "DELETE FROM Device d WHERE d.id =: id")
@NamedQuery(name = "Device.changeDeviceState", query = "UPDATE Device d SET d.readyToStreamData =: state WHERE d.id =: id")

@Entity
@Table(indexes = {
        @Index(name = "device_id", columnList = "id"),
        @Index(name = "device_name", columnList = "name"),
})
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean readyToStreamData = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disk_space_id")
    private DiskSpace diskSpace;

    public Device() {

    }

    public Device(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReadyToStreamData() {
        return readyToStreamData;
    }

    public void setReadyToStreamData(boolean readyToStreamData) {
        this.readyToStreamData = readyToStreamData;
    }

    public DiskSpace getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(DiskSpace diskSpace) {
        this.diskSpace = diskSpace;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", readyToStreamData=" + readyToStreamData +
                ", diskSpace=" + diskSpace +
                '}';
    }

}

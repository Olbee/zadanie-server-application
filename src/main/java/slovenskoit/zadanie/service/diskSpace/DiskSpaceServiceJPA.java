package slovenskoit.zadanie.service.diskSpace;

import slovenskoit.zadanie.entity.Device;
import slovenskoit.zadanie.entity.DiskSpace;
import slovenskoit.zadanie.server.webservice.DiskSpaceServiceRest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class DiskSpaceServiceJPA implements DiskSpaceService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public DiskSpace addDeviceToDiskSpace(Device device, Long disk_space_id) throws DiskSpaceException {
        try {
            DiskSpace diskSpace = (DiskSpace) entityManager.createNamedQuery("DiskSpace.getDiskSpaceById")
                    .setParameter("id", disk_space_id)
                    .getSingleResult();
            diskSpace.addDevice(device);
            return diskSpace;
        } catch (RuntimeException e) {
            throw new DiskSpaceException("Problem adding device to diskSpace");
        }
    }

    @Override
    public DiskSpace deleteDeviceFromDiskSpace(Device device, Long disk_space_id) throws DiskSpaceException {
        try {
            DiskSpace diskSpace = (DiskSpace) entityManager.createNamedQuery("DiskSpace.getDiskSpaceById")
                    .setParameter("id", disk_space_id)
                    .getSingleResult();
            diskSpace.deleteDevice(device);

            entityManager.createNamedQuery("Device.deleteDevice")
                    .setParameter("id", device.getId())
                    .executeUpdate();

            return diskSpace;
        } catch (RuntimeException e) {
            throw new DiskSpaceException("Problem deleting device to diskSpace");
        }
    }

    @Override
    public boolean increaseCapacity(DiskSpace diskSpace, int increaseBy) throws DiskSpaceException {
        try {
            entityManager.createNamedQuery("DiskSpace.updateDiskSpace")
                    .setParameter("newSize", diskSpace.getSize() + increaseBy)
                    .setParameter("id", diskSpace.getId())
                    .executeUpdate();
            return true;
        } catch (RuntimeException e) {
            throw new DiskSpaceException("Problem increasing capacity");
        }
    }

}

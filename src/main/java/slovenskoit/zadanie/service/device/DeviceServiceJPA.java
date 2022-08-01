package slovenskoit.zadanie.service.device;

import slovenskoit.zadanie.entity.Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class DeviceServiceJPA implements DeviceService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void startStream(Device device) throws DeviceServiceException {
        try {
        entityManager.createNamedQuery("Device.changeDeviceState")
                .setParameter("state", true)
                .setParameter("id", device.getId())
                .executeUpdate();
    } catch (RuntimeException e) {
        throw new DeviceServiceException("problem starting stream");
    }
    }

    @Override
    public void stopStream(Device device) throws DeviceServiceException {
        try {
            entityManager.createNamedQuery("Device.changeDeviceState")
                    .setParameter("state", false)
                    .setParameter("id", device.getId())
                    .executeUpdate();
        } catch (RuntimeException e) {
            throw new DeviceServiceException("problem stopping stream");
        }
    }
}

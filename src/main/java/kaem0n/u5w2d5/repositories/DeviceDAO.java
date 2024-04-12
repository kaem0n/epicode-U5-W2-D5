package kaem0n.u5w2d5.repositories;

import kaem0n.u5w2d5.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDAO extends JpaRepository<Device, Long> {
}

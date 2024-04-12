package kaem0n.u5w2d5.services;

import kaem0n.u5w2d5.entities.Device;
import kaem0n.u5w2d5.exceptions.NotFoundException;
import kaem0n.u5w2d5.payloads.DeviceDTO;
import kaem0n.u5w2d5.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO dd;
    @Autowired
    private EmployeeService es;

    public Page<Device> findAll(int page, int size, String sort) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return dd.findAll(p);
    }

    public Device findById(long id) {
        return dd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device save(DeviceDTO payload) {
        Device newDevice = new Device(payload.type(), payload.status(), es.findById(payload.employeeId()));
        return dd.save(newDevice);
    }

    public void delete(long id) {
        Device found = this.findById(id);
        dd.delete(found);
    }

    public Device update(long id, DeviceDTO payload) {
        Device found = this.findById(id);
        found.setType(payload.type());
        found.setStatus(payload.status());
        found.setEmployee(es.findById(payload.employeeId()));
        dd.save(found);
        return found;
    }
}

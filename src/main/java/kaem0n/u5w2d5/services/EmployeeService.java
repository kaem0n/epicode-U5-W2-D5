package kaem0n.u5w2d5.services;

import kaem0n.u5w2d5.entities.Employee;
import kaem0n.u5w2d5.exceptions.BadRequestException;
import kaem0n.u5w2d5.exceptions.NotFoundException;
import kaem0n.u5w2d5.payloads.EmployeeDTO;
import kaem0n.u5w2d5.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO ed;

    public Page<Employee> findAll(int page, int size, String sort) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return ed.findAll(p);
    }

    public Employee findById(long id){
        return ed.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee save(EmployeeDTO payload) {
        if (ed.existsByEmail(payload.email())) throw new BadRequestException("Email " + payload.email() + " is already taken.");
        else {
            Employee newEmployee = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());
            newEmployee.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
            return ed.save(newEmployee);
        }
    }

    public void delete(long id) {
        Employee found = this.findById(id);
        ed.delete(found);
    }

    public Employee update(long id, EmployeeDTO payload) {
        Employee found = this.findById(id);
        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        if (found.getAvatarUrl().contains("ui-avatars.com")) found.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        if (!Objects.equals(found.getEmail(), payload.email()) && !ed.existsByEmail(payload.email())) found.setEmail(payload.email());
        else if (!Objects.equals(found.getEmail(), payload.email()) && ed.existsByEmail(payload.email())) throw new BadRequestException("Email " + payload.email() + " is already taken.");
        return ed.save(found);
    }
}

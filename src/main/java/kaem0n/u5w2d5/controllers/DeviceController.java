package kaem0n.u5w2d5.controllers;

import kaem0n.u5w2d5.entities.Device;
import kaem0n.u5w2d5.exceptions.BadRequestException;
import kaem0n.u5w2d5.payloads.DeviceDTO;
import kaem0n.u5w2d5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService ds;

    @GetMapping
    private Page<Device> getAllDevices(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sort){
        return ds.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    private Device getDeviceById(@PathVariable long id) {
        return ds.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device saveNewDevice(@RequestBody @Validated DeviceDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ds.save(payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable long id) {
        ds.delete(id);
    }

    @PutMapping("/{id}")
    private Device updateDevice(@PathVariable long id, @RequestBody @Validated DeviceDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ds.update(id, payload);
    }
}

package kaem0n.u5w2d5;

import kaem0n.u5w2d5.entities.Device;
import kaem0n.u5w2d5.repositories.DeviceDAO;
import kaem0n.u5w2d5.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DeviceFillRunner implements CommandLineRunner {
    @Autowired
    private DeviceDAO dd;

    @Override
    public void run(String... args) throws Exception {
        if (dd.findAll().size() < 10) {
            Random rnd = new Random();
            String[] type = {"Computer", "Server", "Surveillance"};
            for (int i = 0; i < 10; i++) {
                dd.save(new Device(type[rnd.nextInt(0, type.length)]));
            }
            System.out.println();
            System.out.println("Database has been filled with 10 default devices.");
        }
    }
}

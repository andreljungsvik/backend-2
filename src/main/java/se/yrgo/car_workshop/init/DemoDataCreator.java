package se.yrgo.car_workshop.init;

import se.yrgo.car_workshop.domain.Customer;
import se.yrgo.car_workshop.domain.Vehicle;
import se.yrgo.car_workshop.data.CustomerRepository;
import se.yrgo.car_workshop.data.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataCreator implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public DemoDataCreator(CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Customer customer1 = new Customer();
        customer1.setName("Alice Andersson");
        customer1.setPhoneNumber("0701234567");
        customerRepository.save(customer1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setRegistrationNumber("ABC123");
        vehicle1.setBrand("Volvo");
        vehicle1.setModel("V70");
        vehicle1.setProductionYear(1993);
        customer1.addVehicle(vehicle1);
        vehicleRepository.save(vehicle1);

        Customer customer2 = new Customer();
        customer2.setName("Björn Björk");
        customer2.setPhoneNumber("0737654321");
        customerRepository.save(customer2);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setRegistrationNumber("XYZ789");
        vehicle2.setBrand("Toyota");
        vehicle2.setModel("Corolla");
        vehicle2.setProductionYear(2020);
        customer2.addVehicle(vehicle2);
        vehicleRepository.save(vehicle2);

        System.out.println("Skapade kunder och fordon");
    }
}

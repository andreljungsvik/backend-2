package se.yrgo.car_workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.car_workshop.data.CustomerRepository;
import se.yrgo.car_workshop.data.VehicleRepository;
import se.yrgo.car_workshop.domain.Customer;
import se.yrgo.car_workshop.domain.Vehicle;
import se.yrgo.car_workshop.dto.CustomerDto;
import se.yrgo.car_workshop.dto.VehicleDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkshopController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestParam String name, @RequestParam("phone") String phone) {
        Customer c = new Customer();
        c.setName(name);
        c.setPhoneNumber(phone);
        Customer saved = customerRepository.save(c);
        return ResponseEntity.ok("Customer created with id: " + saved.getId());
    }

    @PostMapping("/vehicles")
    public ResponseEntity<String> createVehicle(@RequestParam String registrationNumber, @RequestParam String brand, @RequestParam String model, @RequestParam int productionYear) {
        if (vehicleRepository.existsByRegistrationNumber(registrationNumber)) {
            return ResponseEntity.badRequest().body("Vehicle with that registration number already exists.");
        }
        Vehicle v = new Vehicle();
        v.setRegistrationNumber(registrationNumber);
        v.setBrand(brand);
        v.setModel(model);
        v.setProductionYear(productionYear);
        Vehicle saved = vehicleRepository.save(v);
        return ResponseEntity.ok("Vehicle created with id: " + saved.getId());
    }

    @PostMapping("/linkvehicletocustomer")
    public ResponseEntity<String> linkVehicleToCustomer(@RequestParam Long customerId,
                                                        @RequestParam Long vehicleId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (customer == null || vehicle == null) {
            return ResponseEntity.badRequest().body("No such customer or vehicle");
        }
        customer.addVehicle(vehicle);
        customerRepository.save(customer);
        return ResponseEntity.ok("Vehicle " + vehicleId + " linked to customer " + customerId);
    }

    @GetMapping("/customers")
    public List<CustomerDto> allCustomersWithVehicles() {
        List<Customer> all = customerRepository.findAllWithVehicles();

        List<CustomerDto> dtos = new ArrayList<>();
        for (Customer c : all) {
            CustomerDto dto = new CustomerDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setPhoneNumber(c.getPhoneNumber());
            for (Vehicle v : c.getVehicles()) {
                VehicleDto vd = new VehicleDto();
                vd.setId(v.getId());
                vd.setRegistrationNumber(v.getRegistrationNumber());
                vd.setBrand(v.getBrand());
                vd.setModel(v.getModel());
                vd.setProductionYear(v.getProductionYear());
                vd.setCustomerId(c.getId());
                dto.getVehicles().add(vd);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    @GetMapping("/vehicles")
    public List<VehicleDto> allVehicles() {
        List<Vehicle> all = vehicleRepository.findAll();

        List<VehicleDto> dtos = new ArrayList<>();
        for (Vehicle v : all) {
            VehicleDto vd = new VehicleDto();
            vd.setId(v.getId());
            vd.setRegistrationNumber(v.getRegistrationNumber());
            vd.setBrand(v.getBrand());
            vd.setModel(v.getModel());
            vd.setProductionYear(v.getProductionYear());
            if (v.getCustomer() != null) {
                vd.setCustomerId(v.getCustomer().getId());
            }
            dtos.add(vd);
        }
        return dtos;
    }

    @GetMapping("/vehicles-by-brand")
    public List<VehicleDto> allVehiclesByBrand(@RequestParam String brand) {
        List<Vehicle> vehiclesByBrand = vehicleRepository.findAllVehiclesByBrand(brand);

        List<VehicleDto> dtos = new ArrayList<>();
        for (Vehicle v : vehiclesByBrand) {
            VehicleDto vd = new VehicleDto();
            vd.setId(v.getId());
            vd.setRegistrationNumber(v.getRegistrationNumber());
            vd.setBrand(v.getBrand());
            vd.setModel(v.getModel());
            vd.setProductionYear(v.getProductionYear());
            if (v.getCustomer() != null) {
                vd.setCustomerId(v.getCustomer().getId());
            }
            dtos.add(vd);
        }
        return dtos;
    }

    @GetMapping("/customer-id")
    public ResponseEntity<String> getCustomerId(@RequestParam String name) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null) {
            return ResponseEntity.ok("Customer id: " + customer.getId());
        }
        return ResponseEntity.badRequest().body("No customer found");
    }
}

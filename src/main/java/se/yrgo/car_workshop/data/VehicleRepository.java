package se.yrgo.car_workshop.data;

import se.yrgo.car_workshop.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.customer.id = :customerId")
    List<Vehicle> findByCustomerId(Long customerId);


    @Query("SELECT v FROM Vehicle v WHERE v.brand = :brand")
    List<Vehicle> findAllVehiclesByBrand(String brand);

    boolean existsByRegistrationNumber(String registrationNumber);
}

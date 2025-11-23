package se.yrgo.car_workshop.data;

import se.yrgo.car_workshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.vehicles")
    List<Customer> findAllWithVehicles();

    Customer findByName(String name);
}

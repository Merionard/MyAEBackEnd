package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepsitory extends JpaRepository<Customer,Long> {
}

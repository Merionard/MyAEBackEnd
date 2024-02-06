package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findAllByUser(User user);
}

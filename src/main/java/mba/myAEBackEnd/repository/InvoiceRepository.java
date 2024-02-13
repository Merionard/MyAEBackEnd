package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {

    List<Invoice> getInvoicesByUser(User user);
}

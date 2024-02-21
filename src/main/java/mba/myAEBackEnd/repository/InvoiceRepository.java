package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {

    List<Invoice> getInvoicesByUser(User user);

    List<Invoice> getInvoicesByStatusIsAndPayedAtIsBetweenAndUser(InvoiceStatus status, ZonedDateTime startDate,ZonedDateTime endDate,User user);

    List<Invoice> getInvoicesByDueDateIsBeforeAndUserAndStatusIsNot(ZonedDateTime today,User user,InvoiceStatus status);
}

package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine,Long> {
}

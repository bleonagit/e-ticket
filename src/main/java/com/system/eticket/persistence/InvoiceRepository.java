package com.system.eticket.persistence;

import com.system.eticket.model.entity.Invoice;
import com.system.eticket.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsInvoiceByTicket(Ticket ticket);
}

package com.system.eticket.service.logic;

import com.system.eticket.model.entity.Ticket;

public interface InvoiceService {
    boolean isPaidTicket(Ticket ticket);
}

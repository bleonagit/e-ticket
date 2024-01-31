package com.system.eticket.service.logic;

import com.system.eticket.model.dto.InvoiceResponse;
import com.system.eticket.model.dto.TicketRequest;
import com.system.eticket.model.dto.TicketResponse;
import com.system.eticket.model.dto.TicketUpdateRequest;
import jakarta.persistence.EntityNotFoundException;

public interface TicketService {
    TicketResponse createTicket(TicketRequest newTicket) throws EntityNotFoundException;

    TicketResponse updateTicket(TicketUpdateRequest updatedTicket) throws EntityNotFoundException, IllegalArgumentException;

    TicketResponse getTicketBySerialNumber(String serialNumber) throws EntityNotFoundException;

    InvoiceResponse payTicket(String serialNumber, double amount) throws EntityNotFoundException;
}

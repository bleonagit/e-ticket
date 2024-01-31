package com.system.eticket.service.logic.impl;

import com.system.eticket.model.entity.Ticket;
import com.system.eticket.persistence.InvoiceRepository;
import com.system.eticket.service.logic.InvoiceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Resource
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isPaidTicket(Ticket ticket) {
        return invoiceRepository.existsInvoiceByTicket(ticket);
    }
}

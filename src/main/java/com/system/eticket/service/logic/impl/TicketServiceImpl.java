package com.system.eticket.service.logic.impl;

import com.system.eticket.model.dto.*;
import com.system.eticket.model.entity.Invoice;
import com.system.eticket.model.entity.Law;
import com.system.eticket.model.entity.Official;
import com.system.eticket.model.entity.Ticket;
import com.system.eticket.model.mapper.InvoiceMapper;
import com.system.eticket.model.mapper.TicketMapper;
import com.system.eticket.persistence.InvoiceRepository;
import com.system.eticket.persistence.TicketRepository;
import com.system.eticket.service.logic.InvoiceService;
import com.system.eticket.service.logic.LawService;
import com.system.eticket.service.logic.OfficialService;
import com.system.eticket.service.logic.TicketService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketRepository ticketRepository;

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private InvoiceRepository invoiceRepository;

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private OfficialService officialService;

    @Resource
    private LawService lawService;

    @Resource
    private InvoiceService invoiceService;

    @Value("${difference.in.days.to.pay.ticket:15}")
    private long differenceInDays;

    @Value("${discount.for.paying.within.days.percentage:50}")
    private double discount;

    @Value("${fee.per.day.for.delay.percentage:2}")
    private double fee;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketRequest newTicket) throws EntityNotFoundException{
        try {
            log.info("TicketServiceImpl::Creating new ticket...");
            Ticket ticket = ticketMapper.toTicket(newTicket);
            if(isValidOfficial(newTicket.getOfficial())){
                Official official = officialService.getOfficialByCode(newTicket.getOfficial().getCode());
                ticket.setOfficial(official);
            }
            if(!CollectionUtils.isEmpty(newTicket.getLaws())){
                Set<Law> laws = lawService.getByIdIn(newTicket.getLaws().stream().map(LawRequest::getId).toList());
                ticket.setLaws(laws);
            }
            ticket.setSerialNumber(UUID.randomUUID().toString());
            ticket.setCreatedDate(ZonedDateTime.now());
            Ticket saved = ticketRepository.saveAndFlush(ticket);
            log.info("TicketServiceImpl::Created new ticket!");
            return ticketMapper.toTicketResponse(saved);
        } catch (EntityNotFoundException exception){
            log.error("TicketServiceImpl::Entity not found");
            throw exception;
        }
    }

    @Override
    @Transactional
    public TicketResponse updateTicket(TicketUpdateRequest updatedTicket) throws EntityNotFoundException, IllegalArgumentException {
        if(StringUtils.isEmpty(updatedTicket.getSerialNumber())){
            log.error("TicketServiceImpl::Ticket serial number is required during update!");
            throw new IllegalArgumentException("Ticket serial number is required during update!");
        }
        try {
            log.info("TicketServiceImpl::Updating ticket with serial number {}", updatedTicket.getSerialNumber());
            Ticket ticket = ticketRepository.getTicketBySerialNumber(updatedTicket.getSerialNumber()).orElseThrow(() -> new EntityNotFoundException("Ticket with serial number was not found!"));
            if(updatedTicket.getAmount() > 0){
                ticket.setAmount(updatedTicket.getAmount());
            }
            if(!StringUtils.isEmpty(updatedTicket.getTicketPlace())){
                ticket.setTicketPlace(updatedTicket.getTicketPlace());
            }
            if(!StringUtils.isEmpty(updatedTicket.getPlateId())){
                ticket.setPlateId(updatedTicket.getPlateId());
            }
            if(!StringUtils.isEmpty(updatedTicket.getBreaker())){
                ticket.setBreaker(updatedTicket.getBreaker());
            }
            if(updatedTicket.getTicketDate() != null){
                ticket.setTicketDate(updatedTicket.getTicketDate());
            }
            if(!StringUtils.isEmpty(updatedTicket.getVehicleType())){
                ticket.setVehicleType(updatedTicket.getVehicleType());
            }
            if(isValidOfficial(updatedTicket.getOfficial())){
                Official official = officialService.getOfficialByCode(updatedTicket.getOfficial().getCode());
                ticket.setOfficial(official);
            }
            if(!CollectionUtils.isEmpty(updatedTicket.getLaws())){
                Set<Law> lawsToAdd = lawService.getByIdIn(updatedTicket.getLaws().stream().map(LawRequest::getId).toList());
                ticket.setLaws(lawsToAdd);
            }
            Ticket saved = ticketRepository.save(ticket);
            log.info("TicketServiceImpl::Updated ticket with serial number {}", updatedTicket.getSerialNumber());
            return ticketMapper.toTicketResponse(saved);
        } catch (EntityNotFoundException exception){
            log.error("TicketServiceImpl::TicketServiceImpl::Entity not found!");
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse getTicketBySerialNumber(String serialNumber) throws EntityNotFoundException {
        try{
            log.info("TicketServiceImpl::Reading ticket {}", serialNumber);
            Ticket ticket = ticketRepository.getTicketBySerialNumber(serialNumber).orElseThrow(EntityNotFoundException::new);
            log.info("TicketServiceImpl::Read ticket {}", serialNumber);
            return ticketMapper.toTicketResponse(ticket);
        } catch (EntityNotFoundException exception){
            log.error("TicketServiceImpl::Ticket with serial number {} was not found", serialNumber);
            throw new EntityNotFoundException("Ticket with serial number not found!");
        }
    }

    @Override
    @Transactional
    public InvoiceResponse payTicket(String serialNumber, double amount) throws EntityNotFoundException, IllegalArgumentException{
        try {
            Ticket ticket = ticketRepository.getTicketBySerialNumber(serialNumber).orElseThrow(() -> new EntityNotFoundException("Ticket with serial number was not found!"));
            boolean isPaid = invoiceService.isPaidTicket(ticket);
            if(isPaid){
                log.info("TicketServiceImpl::Ticket was already paid!");
                return InvoiceResponse.builder().build();
            }
            log.info("TicketServiceImpl::Paying ticket {}!", serialNumber);
            long differenceInSeconds = Math.abs(ticket.getTicketDate().toEpochSecond() - ZonedDateTime.now().toEpochSecond());
            long actualDifferenceInDays = TimeUnit.DAYS.convert(differenceInSeconds, TimeUnit.SECONDS);
            double amountToPay;
            Invoice newInvoice = new Invoice();
            if(actualDifferenceInDays <= differenceInDays){
                newInvoice.setDiscount(discount);
                amountToPay = ticket.getAmount() - discount * ticket.getAmount() / 100;
            } else{
                newInvoice.setFee(fee);
                amountToPay = ticket.getAmount() + actualDifferenceInDays * fee * ticket.getAmount() / 100;
            }
            if(amount != amountToPay){
                log.error("TicketServiceImpl::Your deposit amount is not {}. Please pay this exact amount!", amountToPay);
                throw new IllegalArgumentException("The given amount is not the required one: " + amountToPay);
            }
            newInvoice.setCreatedDate(Timestamp.from(Instant.now()));
            newInvoice.setPayedAmount(amount);
            newInvoice.setInitialAmount(ticket.getAmount());
            newInvoice.setSerialNumber(UUID.randomUUID().toString());
            newInvoice.setTicket(ticket);
            Invoice savedInvoice = invoiceRepository.save(newInvoice);
            log.info("TicketServiceImpl::Payed ticket {}!", serialNumber);
            return invoiceMapper.toInvoiceResponse(savedInvoice);
        } catch (EntityNotFoundException | IllegalArgumentException exception){
            log.error("TicketServiceImpl::Could not pay ticket {} due to an error!", serialNumber);
            throw exception;
        }
    }

    private boolean isValidOfficial(OfficialRequest official){
        return official != null && StringUtils.isNotEmpty(official.getCode());
    }
}

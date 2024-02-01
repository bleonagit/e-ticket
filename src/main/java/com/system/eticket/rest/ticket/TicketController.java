package com.system.eticket.rest.ticket;

import com.system.eticket.model.dto.InvoiceResponse;
import com.system.eticket.model.dto.TicketRequest;
import com.system.eticket.model.dto.TicketResponse;
import com.system.eticket.model.dto.TicketUpdateRequest;
import com.system.eticket.service.logic.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/ticket")
public class TicketController {
    @Resource
    private TicketService ticketService;

    @PostMapping
    @Operation(summary = "Create e new ticket", description = "Create e new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket created successfully!"),
            @ApiResponse(responseCode = "403", description = "Unauthorized, please Sign in!"),
            @ApiResponse(responseCode = "404", description = "Not Found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest newTicket) throws EntityNotFoundException{
        TicketResponse response = ticketService.createTicket(newTicket);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update ticket", description = "Update ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Bad request!"),
            @ApiResponse(responseCode = "403", description = "Unauthorized, please Sign in!"),
            @ApiResponse(responseCode = "404", description = "Not Found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<TicketResponse> updateTicket(@RequestBody TicketUpdateRequest newTicket) throws EntityNotFoundException, IllegalArgumentException{
        TicketResponse response = ticketService.updateTicket(newTicket);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get ticket", description = "Get ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket read successfully!"),
            @ApiResponse(responseCode = "403", description = "Unauthorized, please Sign in!"),
            @ApiResponse(responseCode = "404", description = "Not Found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<TicketResponse> getTicketBySerialNumber(@RequestParam String serialNumber) throws EntityNotFoundException{
        TicketResponse response = ticketService.getTicketBySerialNumber(serialNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/payTicket")
    @Operation(summary = "Pay ticket", description = "Pay ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket paid successfully!"),
            @ApiResponse(responseCode = "403", description = "Unauthorized, please Sign in!"),
            @ApiResponse(responseCode = "404", description = "Not Found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<InvoiceResponse> payTicket(@RequestParam(name = "serialNumber") String serialNumber, @RequestParam(name = "amount") double amount) throws EntityNotFoundException{
        InvoiceResponse response = ticketService.payTicket(serialNumber, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

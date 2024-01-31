package com.system.eticket.model.mapper;

import com.system.eticket.model.dto.TicketRequest;
import com.system.eticket.model.dto.TicketResponse;
import com.system.eticket.model.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public abstract class TicketMapper {
    @Mappings({
            @Mapping(target = "official", ignore = true),
            @Mapping(target = "invoice", ignore = true),
            @Mapping(target = "laws", ignore = true)
    })
    public abstract Ticket toTicket(TicketRequest ticketRequest);

    public abstract TicketResponse toTicketResponse(Ticket ticket);
}

package com.system.eticket.model.mapper;

import com.system.eticket.model.dto.InvoiceResponse;
import com.system.eticket.model.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper
public abstract class InvoiceMapper {
    public abstract InvoiceResponse toInvoiceResponse(Invoice invoice);
}

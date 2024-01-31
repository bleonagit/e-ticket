package com.system.eticket.service.logic;

import com.system.eticket.model.entity.Official;
import jakarta.persistence.EntityNotFoundException;

public interface OfficialService {
    Official getOfficialByCode(String code) throws EntityNotFoundException;
}

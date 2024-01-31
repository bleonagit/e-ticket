package com.system.eticket.service.logic;

import com.system.eticket.model.entity.Law;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Set;

public interface LawService {
    Set<Law> getByIdIn(List<Long> ids) throws EntityNotFoundException;
}

package com.system.eticket.service.logic.impl;

import com.system.eticket.model.entity.Official;
import com.system.eticket.persistence.OfficialRepository;
import com.system.eticket.service.logic.OfficialService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OfficialServiceImpl implements OfficialService {
    @Resource
    private OfficialRepository officialRepository;

    @Override
    @Transactional(readOnly = true)
    public Official getOfficialByCode(String code) throws EntityNotFoundException{
        try {
            return officialRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException exception){
            log.error("Official with code {} was not found", code);
            throw new EntityNotFoundException("Official with code not found!");
        }
    }
}

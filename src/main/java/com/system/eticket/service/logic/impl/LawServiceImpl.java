package com.system.eticket.service.logic.impl;

import com.system.eticket.model.entity.Law;
import com.system.eticket.persistence.LawRepository;
import com.system.eticket.service.logic.LawService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class LawServiceImpl implements LawService {
    @Resource
    private LawRepository lawRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<Law> getByIdIn(List<Long> ids) throws EntityNotFoundException {
        try {
            Optional<Set<Law>> laws = lawRepository.findByIdIn(ids);
            if(!laws.isPresent() || laws.get().isEmpty()){
                throw new EntityNotFoundException();
            }
            return laws.get();
        } catch (EntityNotFoundException exception){
            log.error("No Law with ids {} exist", ids);
            throw new EntityNotFoundException("No Law with these ids " + ids + " exist");
        }
    }
}

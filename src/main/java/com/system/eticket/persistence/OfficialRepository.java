package com.system.eticket.persistence;

import com.system.eticket.model.entity.Official;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficialRepository extends JpaRepository<Official, Long> {
    Optional<Official> findByCode(String code);
}

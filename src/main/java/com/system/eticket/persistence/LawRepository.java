package com.system.eticket.persistence;

import com.system.eticket.model.entity.Law;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LawRepository extends JpaRepository<Law, Long> {
    Optional<Set<Law>> findByIdIn(List<Long> ids);
}

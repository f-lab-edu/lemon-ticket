package com.flab.lemonticket.repository;

import com.flab.lemonticket.entity.PerformerEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerEventRepository extends JpaRepository<PerformerEvent, Long> {
    void deleteByEventId(Long eventId);
}

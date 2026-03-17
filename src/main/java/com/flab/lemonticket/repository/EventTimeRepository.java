package com.flab.lemonticket.repository;

import com.flab.lemonticket.entity.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTimeRepository extends JpaRepository<EventTime, Long> {
    void deleteByEventId(Long eventId);
}

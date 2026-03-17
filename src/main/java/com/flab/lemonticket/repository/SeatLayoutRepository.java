package com.flab.lemonticket.repository;

import com.flab.lemonticket.entity.SeatLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatLayoutRepository extends JpaRepository<SeatLayout, Long> {
    void deleteByEventId(Long eventId);
}

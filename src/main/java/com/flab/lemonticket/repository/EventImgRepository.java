package com.flab.lemonticket.repository;

import com.flab.lemonticket.entity.EventImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImgRepository extends JpaRepository<EventImg, Long> {
    void deleteByEventId(Long eventId);
}

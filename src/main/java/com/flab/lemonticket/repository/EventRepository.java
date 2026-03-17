package com.flab.lemonticket.repository;


import com.flab.lemonticket.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e JOIN PerformerEvent pe ON e.eventId = pe.eventId JOIN Performer p ON pe.performerId = p.performerId WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :performerName, '%'))")
    Page<Event> findByPerformerNameContainingIgnoreCase(String performerName, Pageable pageable);

    Page<Event> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
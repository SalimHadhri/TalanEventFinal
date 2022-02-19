package tn.talan.academyApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.talan.academyApp.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByNameEvent(String eventName);
    List<Event> findByStatus(String status);
    List<Event> findByAnnulée(boolean annulée);

}

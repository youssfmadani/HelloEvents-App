package com.example.helloevents.repositories;



import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory(String category);
    List<EventDTO> findByLocationContainingIgnoreCase(String location);
    List<EventDTO> findByTitleContainingIgnoreCase(String keyword);
}

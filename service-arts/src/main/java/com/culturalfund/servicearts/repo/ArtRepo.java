package com.culturalfund.servicearts.repo;

import com.culturalfund.servicearts.repo.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArtRepo extends JpaRepository<Art, Long> {

}

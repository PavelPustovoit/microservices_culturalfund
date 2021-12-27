package com.culturalfund.servicefeedback.repo;

import com.culturalfund.servicefeedback.repo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}

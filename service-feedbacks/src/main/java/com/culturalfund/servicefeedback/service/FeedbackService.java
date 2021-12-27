package com.culturalfund.servicefeedback.service;

import com.culturalfund.servicefeedback.repo.FeedbackRepo;
import com.culturalfund.servicefeedback.repo.model.Feedback;
import com.culturalfund.servicefeedback.repo.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public List<Feedback> fetchAll() {
        return feedbackRepo.findAll();
    }

    public Feedback fetchById(long id) throws IllegalArgumentException {
        final Optional<Feedback> maybeClaim = feedbackRepo.findById(id);

        if (maybeClaim.isEmpty()) throw new IllegalArgumentException("Claim not found");
        else return maybeClaim.get();
    }

    public long create(String description, long userId, Date dateCreate, Status status) {

        final Feedback claim = new Feedback(description, userId, dateCreate, status);
        final Feedback savedClaim = feedbackRepo.save(claim);

        return savedClaim.getId();
    }

    public void update(Long id, Status status) throws IllegalArgumentException {

        final Optional<Feedback> maybeClaim = feedbackRepo.findById(id);
        if (maybeClaim.isEmpty()) throw new IllegalArgumentException("Claim not found");

        final Feedback claim = maybeClaim.get();

        if (status != null) claim.setStatus(status);
        feedbackRepo.save(claim);
    }

    public void delete(Long id) {
        feedbackRepo.deleteById(id);
    }
}

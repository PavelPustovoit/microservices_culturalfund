package com.culturalfund.servicefeedback.api;

import com.culturalfund.servicefeedback.api.dto.FeedbackDto;
import com.culturalfund.servicefeedback.repo.model.Feedback;
import com.culturalfund.servicefeedback.repo.model.Status;
import com.culturalfund.servicefeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> index() {
        final List<Feedback> feedbacks = feedbackService.fetchAll();

        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> show(@PathVariable long id) {
        try {
            final Feedback feedback = feedbackService.fetchById(id);
            return ResponseEntity.ok(feedback);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FeedbackDto feedbackDto) {
        final String description = feedbackDto.getDescription();
        final Status status = feedbackDto.getStatus();
        final long userId = feedbackDto.getUserId();
        final Date dateCreate = feedbackDto.getDateCreate();

        final long id = feedbackService.create(description, userId, dateCreate, status);

        final String location = String.format("/feedbacks/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody FeedbackDto feedbackDto) {

        final Status status = feedbackDto.getStatus();

        try {
            feedbackService.update(id, status);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

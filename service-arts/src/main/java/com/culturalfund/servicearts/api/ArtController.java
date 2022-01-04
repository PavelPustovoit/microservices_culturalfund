package com.culturalfund.servicearts.api;


import com.culturalfund.servicearts.ViewObject.ResponseTemplateV;
import com.culturalfund.servicearts.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/arts")
public final class ArtController {

    private final ArtService artService;

    @GetMapping
    public ResponseEntity<List<com.culturalfund.servicearts.repo.model.Art>> index() {
        final List<com.culturalfund.servicearts.repo.model.Art> arts = artService.fetchAll();

        return ResponseEntity.ok(arts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.culturalfund.servicearts.repo.model.Art> show(@PathVariable long id) {
        try {
            final com.culturalfund.servicearts.repo.model.Art art = artService.fetchById(id);
            return ResponseEntity.ok(art);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.culturalfund.servicearts.api.dto.Art art) {
        final String author = art.getAuthor();
        final String technique = art.getTechnique();
        final String title = art.getTitle();
        final String style = art.getStyle();
        final Date dateCreate = art.getDateCreate();
        final String description = art.getDescription();
        final Long userId = art.getUserId();
        final LocalDate addAt = LocalDate.now();
        final long id = artService.create(title, author, dateCreate, style, technique, description, userId, addAt);

        final String location = String.format("/arts/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.culturalfund.servicearts.api.dto.Art art) {
        final String author = art.getAuthor();
        final String technique = art.getTechnique();
        final String title = art.getTitle();
        final String style = art.getStyle();
        final Date dateCreate = art.getDateCreate();
        final String description = art.getDescription();

        try {
            artService.update(id, title, author, dateCreate, style, technique, description);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        artService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view/{id}")
    public ResponseTemplateV getArtsWithUser(@PathVariable long id){
        return artService.getArtsWithUser(id);
    }
}

package com.culturalfund.servicearts.service;

import com.culturalfund.servicearts.repo.ArtRepo;
import com.culturalfund.servicearts.repo.model.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtService {

    private final ArtRepo artRepo;

    public List<Art> fetchAll() {
       return artRepo.findAll();
    }

    public Art fetchById(long id) throws IllegalArgumentException {
        final Optional<Art> maybeArt = artRepo.findById(id);

        if (maybeArt.isEmpty()) throw new IllegalArgumentException("Art not found");
        else return maybeArt.get();
    }

    public long create(String title, String author, Date dateCreate, String style,
                       String technique, String description) {

        final Art art = new Art(title, author, dateCreate, style, technique, description);
        final Art savedArt = artRepo.save(art);

        return savedArt.getId();
    }


    public void update(Long id, String title, String author, Date dateCreate, String style,
                       String technique, String description) throws IllegalArgumentException {

        final Optional<Art> maybeArt = artRepo.findById(id);
        if (maybeArt.isEmpty()) throw new IllegalArgumentException("Art not found");

        final Art art = maybeArt.get();
        if (title != null && !title.isBlank()) art.setTitle(title);
        if (author != null && !author.isBlank()) art.setAuthor(author);
        if (style != null && !style.isBlank()) art.setStyle(style);
        if (technique != null && !technique.isBlank()) art.setTechnique(technique);
        if (description != null && !description.isBlank()) art.setDescription(description);
        if (dateCreate != null) art.setDateCreate(dateCreate);
        artRepo.save(art);
    }

    public void delete(Long id) {
        artRepo.deleteById(id);
    }

/*    {
        "title": "Old art",
            "author": "Volodymyr",
            "dateCreate": "1998-06-10",
            "style": "Classic",
            "technique": "Ceramic",
            "description": "Incredible"
    }
 */
}

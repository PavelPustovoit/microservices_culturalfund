package com.culturalfund.servicearts.service;

import com.culturalfund.servicearts.ViewObject.ResponseTemplateV;
import com.culturalfund.servicearts.ViewObject.User;
import com.culturalfund.servicearts.repo.ArtRepo;
import com.culturalfund.servicearts.repo.model.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtService {

    private final ArtRepo artRepo;

    @Autowired
    private RestTemplate restTemplate;

    public List<Art> fetchAll() {
       return artRepo.findAll();
    }

    public Art fetchById(Long id) throws IllegalArgumentException {
        final Optional<Art> maybeArt = artRepo.findById(id);

        if (maybeArt.isEmpty()) throw new IllegalArgumentException("Art not found");
        else return maybeArt.get();
    }

    public long create(String title, String author, Date dateCreate, String style, String technique, String description, long userId, LocalDate addAt) {

        final Art art = new Art(title, author, dateCreate, style, technique, description, userId, addAt);
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

    public ResponseTemplateV getArtsWithUser(Long Id){
        ResponseTemplateV view = new ResponseTemplateV();
        final Art art;
        final Optional<Art> maybeArt = artRepo.findById(Id);
        if(maybeArt.isEmpty()) throw new IllegalArgumentException("Art not found");
        else {
            art = maybeArt.get();
            final User user = restTemplate.getForObject("http://localhost:8080/users/" + art.getUserId(), User.class);

            view.setArt(art);
            view.setUser(user);
            return view;
        }
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

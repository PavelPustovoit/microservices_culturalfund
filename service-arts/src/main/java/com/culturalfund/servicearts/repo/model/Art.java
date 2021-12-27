package com.culturalfund.servicearts.repo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "arts")
public final class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private Date dateCreate;
    private String style;
    private String technique;
    private String description;

    public Art() {
    }

    public Art(String title, String author, Date dateCreate, String style,
               String technique, String description) {
        this.title = title;
        this.author = author;
        this.dateCreate = dateCreate;
        this.style = style;
        this.technique = technique;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

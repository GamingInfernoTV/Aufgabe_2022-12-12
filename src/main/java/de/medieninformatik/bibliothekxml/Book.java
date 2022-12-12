package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

/**
 * Storage-Klasse zum Speichern von Buch-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter und Setter sind nicht extra kommentiert,
 * da diese Kommentare sehr redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
@XmlType(namespace = "de.mi.library")
public class Book {
    private List<Author> authors;
    private String title;
    private String isbn;
    private String publisher;
    private int year;
    private int edition;
    private List<Genre> genres;

    @XmlElementWrapper(required = true)
    @XmlElement(name = "author", required = true)
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @XmlElement(required = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(required = true)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlElement(required = true)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @XmlElement(required = true)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @XmlElement(required = true)
    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @XmlElementWrapper(required = true)
    @XmlElement(name = "genre", required = true)
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}

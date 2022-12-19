package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.List;
import java.util.Objects;

/**
 * Storage-Klasse zum Speichern von Buch-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Neben grundlegenden Daten werden auch {@link Author Autoren} und {@link Genre Genres} des Buches gespeichert
 * <p>
 * Getter, Setter, {@link Object#equals(Object)} und {@link Object#hashCode()}
 * sind nicht extra kommentiert, da diese Methoden mittels des IntelliJ-Wizards automatisch erstellt wurden
 * und die Kommentare dazu zum großen Teil redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public final class Book {
    private List<Author> authors;
    private String title;
    private String isbn;
    private String publisher;
    private int year;
    private int edition;
    private List<Genre> genres;

    /**
     * Standard-Konstruktor
     */
    public Book() {
        authors = null;
        title = null;
        isbn = null;
        publisher = null;
        year = 0;
        edition = 0;
        genres = null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return year == book.year &&
               edition == book.edition &&
               Objects.equals(authors, book.authors) &&
               Objects.equals(title, book.title) &&
               Objects.equals(isbn, book.isbn) &&
               Objects.equals(publisher, book.publisher) &&
               Objects.equals(genres, book.genres);
    }

    @Override
    public int hashCode() {
        int result = authors != null ? authors.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + edition;
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }
}

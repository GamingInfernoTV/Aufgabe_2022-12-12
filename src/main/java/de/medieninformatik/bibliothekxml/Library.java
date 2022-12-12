package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

/**
 * Storage-Klasse zum Speichern von Bibliothek-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter und Setter sind nicht extra kommentiert,
 * da diese Kommentare sehr redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
@XmlRootElement
@XmlType(namespace = "de.mi.library")
public class Library {
    private List<Book> books;

    @XmlElementWrapper(required = true)
    @XmlElement(name = "book", required = true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

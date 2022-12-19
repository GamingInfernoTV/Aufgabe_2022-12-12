package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Objects;

/**
 * Storage-Klasse zum Speichern von Bibliothek-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter, Setter, {@link Object#equals(Object)} und {@link Object#hashCode()}
 * sind nicht extra kommentiert, da diese Methoden mittels des IntelliJ-Wizards automatisch erstellt wurden
 * und die Kommentare dazu zum großen Teil redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
@XmlRootElement
public final class Library {
    private List<Book> books;

    /**
     * Standard-Konstruktor
     */
    public Library() {
        books = null;
    }

    @XmlElementWrapper(required = true)
    @XmlElement(name = "book", required = true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        return Objects.equals(books, library.books);
    }

    @Override
    public int hashCode() {
        return books != null ? books.hashCode() : 0;
    }
}

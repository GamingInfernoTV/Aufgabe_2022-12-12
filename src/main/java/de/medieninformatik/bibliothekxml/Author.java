package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

/**
 * Storage-Klasse zum Speichern von Autor-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter, Setter, {@link Object#equals(Object)} und {@link Object#hashCode()}
 * sind nicht extra kommentiert, da diese Methoden mittels des IntelliJ-Wizards automatisch erstellt wurden
 * und die Kommentare dazu zum großen Teil redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public final class Author {
    private String firstName;
    private String lastName;

    /**
     * Standard-Konstruktor
     */
    public Author() {
        firstName = null;
        lastName = null;
    }

    @XmlElement(required = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(required = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(firstName, author.firstName) &&
               Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}

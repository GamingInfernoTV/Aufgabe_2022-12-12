package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Storage-Klasse zum Speichern von Autor-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter und Setter sind nicht extra kommentiert,
 * da diese Kommentare sehr redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
@XmlType(namespace = "de.mi.library")
public class Author {
    private String firstName;
    private String lastName;

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
}

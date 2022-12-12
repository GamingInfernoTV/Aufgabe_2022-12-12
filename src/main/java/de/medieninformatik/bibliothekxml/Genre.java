package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Storage-Klasse zum Speichern von Genre-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter und Setter sind nicht extra kommentiert,
 * da diese Kommentare sehr redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
@XmlType(namespace = "de.mi.library")
public class Genre {
    private String name;

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

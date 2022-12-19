package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

/**
 * Storage-Klasse zum Speichern von Genre-Daten mittels einfacher Getter und Setter
 * und entsprechenden Annotationen für XML-Parsing
 * <p>
 * Getter, Setter, {@link Object#equals(Object)} und {@link Object#hashCode()}
 * sind nicht extra kommentiert, da diese Methoden mittels des IntelliJ-Wizards automatisch erstellt wurden
 * und die Kommentare dazu zum großen Teil redundant ausfallen würden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public final class Genre {
    private String name;

    /**
     * Standard-Konstruktor
     */
    public Genre() {
        name = null;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

package de.medieninformatik.bibliothekxml;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility-Klasse zum Erzeugen eine {@link Library} mit zufalls-generierten {@link Book Buch}-Daten;
 * zum Generieren wird die {@link Faker Java Faker} Bibliothek genutzt, erzeugt werden die Daten in deutscher Sprache
 * (soweit möglich)
 */
@SuppressWarnings("java:S109")
public final class LibraryFactory {
    private static final Faker FAKER = new Faker(Locale.GERMAN);

    /**
     * Privater Konstruktor; Instanziierung für Utility-Klasse nicht nötig
     */
    private LibraryFactory() {
    }

    /**
     * Erzeugt eine neue Instanz von {@link Library} mit 30 zufalls-generierten {@link Book Büchern}
     *
     * @return Die erzeugte Library
     */
    public static Library create() {
        var library = new Library();
        library.setBooks(getBooks());
        return library;
    }

    /**
     * Erzeugt eine {@link List Liste} von 30 {@link Book Büchern} mit zufälligen Daten
     *
     * @return Die Liste mit erzeugten Büchern
     */
    private static List<Book> getBooks() {
        return Stream.generate(FAKER::book)
                .map((com.github.javafaker.Book fakerBook) -> {
                    var book = new Book();
                    book.setAuthors(getAuthors());
                    book.setTitle(fakerBook.title());
                    book.setIsbn(FAKER.code().isbn10(true));
                    book.setPublisher(fakerBook.publisher());
                    book.setYear(FAKER.number().numberBetween(1800, 2023));
                    book.setEdition(FAKER.number().randomDigitNotZero());
                    book.setGenres(getGenres(fakerBook));
                    return book;
                })
                .limit(30)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Erzeugt eine {@link List Liste} von 1 bis 3 {@link Author Autoren} mit zufälligen Daten
     *
     * @return Die Liste mit erzeugten Autoren
     */
    private static List<Author> getAuthors() {
        return Stream.generate(FAKER::name)
                .map((Name fakerName) -> {
                    var author = new Author();
                    author.setFirstName(fakerName.firstName());
                    author.setLastName(fakerName.lastName());
                    return author;
                })
                .limit(FAKER.number().numberBetween(1, 4))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Erzeugt eine {@link List Liste} mit 1 bis 3 zufälligen {@link Genre Genres}
     * auf Grundlage eines {@link com.github.javafaker.Book Buches}
     *
     * @param fakerBook Das Buch, auf Grundlage dessen die Genres erzeugt werden sollen
     * @return Die Liste mit erzeugten Genres
     */
    private static List<Genre> getGenres(com.github.javafaker.Book fakerBook) {
        return Stream.generate(fakerBook::genre)
                .map((String fakerGenre) -> {
                    var genre = new Genre();
                    genre.setName(fakerGenre);
                    return genre;
                })
                .limit(FAKER.number().numberBetween(1, 4))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}

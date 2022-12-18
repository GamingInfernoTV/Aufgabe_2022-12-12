package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Hauptklasse des Programms
 *
 * @author Malte Kasolowsky <code>m30114</code>
 * @author Aaron Pöhlmann <code>m30115</code>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = {
        "authors",
        "title",
        "isbn",
        "publisher",
        "year",
        "edition",
        "genres"
})
public class Main {
    private static final String SCHEMA_FILE_NAME = "library-schema.xsd";
    /**
     * TODO: method description
     *
     * @param args nicht benutzt
     */
    public static void main(String[] args) {
        /* TODO:
            - create schema
            - create library sample data
            - create xml file from sample data
            - validate sample data against generated schema
            - create json from sample data
            - test xml and json output
            - compare xml and json output with sample data
         */
    }
}

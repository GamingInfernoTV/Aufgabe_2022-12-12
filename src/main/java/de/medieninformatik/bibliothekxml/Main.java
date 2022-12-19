package de.medieninformatik.bibliothekxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.UnmarshalException;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hauptklasse des Programms
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public class Main {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    static {
        LOGGER.setLevel(Level.ALL);
    }

    /**
     * Hauptmethode; erstellt mittels {@link LibraryFactory#create()} eine Beispiel-{@link Library},
     * welche mittels {@link JAXBContext JAXB} und {@link ObjectMapper Jackson} in eine entsprechende
     * Datei eingelesen und danach wieder ausgelesen wird,
     * wonach die beiden Resultate auf Gleichheit geprüft werden.
     * Bei Auslesen der Xml-Datei wird zudem das Xml-Objekt gegen ein entsprechend erzeugtes Schema validiert
     *
     * @param args nicht benutzt
     */
    public static void main(String[] args) {
        try {
            // create context, mapper, schema and sample data
            final JAXBContext context = JAXBContext.newInstance(Library.class);
            final Schema librarySchema = createSchema(context);
            final ObjectMapper objectMapper = new ObjectMapper();
            final Library sampleData = LibraryFactory.create();

            // xml marshalling and unmarshalling
            final File marshalledXml = marshallLibrary(sampleData, context);
            final Library unmarshalledXml = unmarshallLibrary(marshalledXml, context, librarySchema);

            // json marshalling and unmarshalling
            final File marshalledJson = marshallLibrary(sampleData, objectMapper);
            final Library unmarshalledJson = unmarshallLibrary(marshalledJson, objectMapper);

            // compare the results of both operations
            if (Objects.equals(unmarshalledXml, unmarshalledJson)) {
                LOGGER.info("marshalling and unmarshalling was successful");
            } else {
                LOGGER.warning("marshalling and unmarshalling failed");
            }
        } catch (UnmarshalException e) {
            LOGGER.log(Level.SEVERE, "validation of marshalled xml file failed", e);
        } catch (JAXBException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, "failure during marshalling and unmarshalling", e);
        }
    }

    /**
     * Erzeugt ein XML-{@link Schema} aus einem vorgegebenen {@link JAXBContext}
     *
     * @param context Der Context, aus welchem das Schema generiert werden soll
     * @return Das erzeugte Schema
     * @throws IOException  Wenn von {@link JAXBContext#generateSchema(SchemaOutputResolver)}
     *                      eine IOException geworfen wird
     * @throws SAXException Wenn bei {@link SchemaFactory#newSchema(Source)}
     *                      eine SAXException geworfen würde
     */
    private static Schema createSchema(JAXBContext context) throws IOException, SAXException {
        var schemaFile = new File("library-schema.xsd");
        var schemaResolver = new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) {
                return new StreamResult(schemaFile);
            }
        };
        var source = new StreamSource(schemaFile);
        context.generateSchema(schemaResolver);
        return SchemaFactory.newDefaultInstance().newSchema(source);
    }

    /**
     * Wandelt ein {@link Library}-Objekt mittels eines {@link JAXBContext}
     * in eine Xml-{@link File} um
     *
     * @param library Das umzuwandelnde Library-Objekt
     * @param context Der Context, mit welchem das Objekt umgewandelt wird
     * @return Eine Xml-Datei, welche die Daten des umgewandelten Objekts beinhaltet
     * @throws JAXBException Wenn beim Umwandeln eine JAXBException geworfen wird
     */
    private static File marshallLibrary(Library library, JAXBContext context) throws JAXBException {
        var file = new File("library-data.xml");
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(library, file);
        return file;
    }

    /**
     * Liest eine {@link File} ein und wandelt diese mittels des vorgegebenen {@link JAXBContext}
     * in ein {@link Library}-Objekt um, wobei beim Umwandeln die Datei gegen das übergebene {@link Schema}
     * validiert wird
     *
     * @param file Die einzulesende Xml-Datei
     * @param context Der Context, mit welchem die Datei umgewandelt wird
     * @param schema Das Schema, gegen das welches die Datei validiert wird
     * @return Die umgewandelte Library
     * @throws JAXBException Wenn beim Umwandeln ein JAXBException geworfen wird
     */
    private static Library unmarshallLibrary(File file, JAXBContext context, Schema schema) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Library) unmarshaller.unmarshal(file);
    }

    /**
     * Wandelt ein {@link Library}-Objekt mittels eines {@link ObjectMapper}
     * in eine Json-{@link File} um
     *
     * @param library Das umzuwandelnde Library-Objekt
     * @param objectMapper Der ObjectMapper, mit welchem das Objekt umgewandelt wird
     * @return Eine Json-Datei, welche die Daten des umgewandelten Objekts beinhaltet
     * @throws IOException Wenn beim Schreiben der Datei eine IOException geworfen wird
     */
    private static File marshallLibrary(Library library, ObjectMapper objectMapper) throws IOException {
        var file = new File("library-data.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, library);
        return file;
    }

    /**
     * Liest eine {@link File} ein und wandelt diese mittels eines {@link ObjectMapper}
     * in ein {@link Library}-Objekt um
     *
     * @param file         Die einzulesende Json-Datei
     * @param objectMapper Der ObjectMapper zum Umwandeln der Datei
     * @return Ein Library-Objekt
     * @throws IOException Wenn beim Einlesen ein Fehler auftritt
     */
    private static Library unmarshallLibrary(File file, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(file, Library.class);
    }
}

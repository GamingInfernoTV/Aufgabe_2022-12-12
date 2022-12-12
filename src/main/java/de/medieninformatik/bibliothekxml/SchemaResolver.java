package de.medieninformatik.bibliothekxml;

import jakarta.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Implementierung eines {@link SchemaOutputResolver} zum Erstellen eines XML-Schemas
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public class SchemaResolver extends SchemaOutputResolver {
    /**
     * Decides where the schema file (of the given namespace URI)
     * will be written, and return it as a {@link Result} object.
     *
     * <p>
     * This method is called only once for any given namespace.
     * IOW, all the components in one namespace is always written
     * into the same schema document.
     *
     * @param namespaceUri      The namespace URI that the schema declares.
     *                          Can be the empty string, but never be null.
     * @param suggestedFileName A Jakarta XML Binding implementation generates an unique file name (like "schema1.xsd")
     *                          for the convenience of the callee. This name can be
     *                          used for the file name of the schema, or the callee can just
     *                          ignore this name and come up with its own name.
     *                          This is just a hint.
     * @return a {@link Result} object that encapsulates the actual destination
     * of the schema.
     * <p>
     * If the {@link Result} object has a system ID, it must be an
     * absolute system ID. Those system IDs are relativized by the caller and used
     * for {@literal <xs:import>} statements.
     * <p>
     * If the {@link Result} object does not have a system ID, a schema
     * for the namespace URI is generated but it won't be explicitly
     * {@literal <xs:import>}ed from other schemas.
     * <p>
     * If {@code null} is returned, the schema generation for this
     * namespace URI will be skipped.
     */
    @Override
    public Result createOutput(String namespaceUri, String suggestedFileName) {
        return new StreamResult(new File(".", suggestedFileName));
    }
}

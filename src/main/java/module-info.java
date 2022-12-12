module BibliothekXML.main {
    requires java.xml;
    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens de.medieninformatik.bibliothekxml to jakarta.xml.bind;
    exports de.medieninformatik.bibliothekxml;
}
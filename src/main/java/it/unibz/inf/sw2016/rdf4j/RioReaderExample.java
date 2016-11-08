package it.unibz.inf.sw2016.rdf4j;


import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RioReaderExample {
    public static void main(String[] args) throws IOException {

        final File file = new File("src/main/resources/file.xml");

        // open our input document
        //java.net.URL documentUrl = new URL("http://example.org/example.ttl");
        InputStream inputStream = new FileInputStream(file);
        // create a parser for Turtle and a writer for RDF/XML
        RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);

        Model results = Rio.parse(inputStream, file.toURL().toString(), RDFFormat.RDFXML);
        results.forEach(System.out::println);

//        Model model = new LinkedHashModel();
//        rdfParser.setRDFHandler(new StatementCollector(model));
//
//        try {
//            rdfParser.parse(inputStream, file.toURI().toString());
//        } catch (IOException | RDFParseException | RDFHandlerException e) {
//            // handle IO problems (e.g. the file could not be read)
//        }
//
//        model.forEach(System.out::println);
    }

}

package it.unibz.inf.sw2016.rdf4j;


import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ModelBuilderDemo {
    public static void main(String[] args) throws FileNotFoundException {
        ModelBuilder builder = new ModelBuilder();

// set some namespaces
        builder.setNamespace("ex", "http://example.org/").setNamespace(FOAF.NS);

        builder.namedGraph("ex:graph1")      // add a new named graph to the model
                .subject("ex:john")        // add  several statements about resource ex:john
                .add(FOAF.NAME, "John")  // add the triple (ex:john, foaf:name "John") to the named graph
                .add(FOAF.AGE, 42)
                .add(FOAF.MBOX, "john@example.org");

// add a triple to the default graph
        builder.defaultGraph().add("ex:graph1", RDF.TYPE, "ex:Graph");
        final Model model = builder.build();

//        FileOutputStream out = new FileOutputStream("src/main/resources/file.xml");
//        RDFWriter writer = Rio.createWriter(RDFFormat.RDFXML, out);

        FileOutputStream out = new FileOutputStream("src/main/resources/file.nquads");

        Rio.write(model, out, RDFFormat.NQUADS);


//        RDFWriter writer = Rio.createWriter(RDFFormat.NQUADS, out);
//
//
//
//        try {
//            writer.startRDF();
//            for (Statement st: model) {
//                writer.handleStatement(st);
//            }
//            writer.endRDF();
//        }
//        catch (RDFHandlerException e) {
//            // oh no, do something!
//        }
    }
}

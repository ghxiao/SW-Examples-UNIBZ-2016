package it.unibz.inf.sw2016.rdf4j;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;

public class ModelAPIDemo {
    public static void main(String[] args) {

        ValueFactory factory = SimpleValueFactory.getInstance();
        IRI bob = factory.createIRI("http://example.org/bob");
        IRI name = factory.createIRI("http://example.org/name");
        Literal bobsName = factory.createLiteral("Bob");
        Statement nameStatement = factory.createStatement(bob, name, bobsName);
        Statement typeStatement = factory.createStatement(bob, RDF.TYPE, FOAF.PERSON);


        Model model = new LinkedHashModel();
        model.add(typeStatement);
        model.add(bob, name, bobsName);

        model.forEach(System.out::println);

//        for(Statement triple: model){
//            System.out.println(triple);
//        }


    }
}

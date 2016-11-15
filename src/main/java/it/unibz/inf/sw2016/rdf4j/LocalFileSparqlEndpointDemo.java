package it.unibz.inf.sw2016.rdf4j;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.File;
import java.io.IOException;

public class LocalFileSparqlEndpointDemo {
    public static void main(String[] args) throws IOException {
        Repository repo = new SailRepository(new MemoryStore());
        //Repository repo = new SailRepository(new MemoryStore());

        repo.initialize();

        final RepositoryConnection connection = repo.getConnection();

        connection.add(new File("src/main/resources/animal.ttl"), null, RDFFormat.TURTLE);

        try (RepositoryConnection conn = repo.getConnection()) {
            String queryString =
                    "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n" +
                            "SELECT DISTINCT * WHERE {\n" +
                            "?X rdfs:subClassOf ?Y .\n" +
                            "}";
            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            //TupleQueryResult result = tupleQuery.evaluate();
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    BindingSet bindingSet = result.next();
                    System.out.println(bindingSet);

                    //Value valueOfX = bindingSet.getValue("N");
                    //Value valueOfY = bindingSet.getValue("O");
                    //System.out.println("x:" + valueOfX + ", y:" + valueOfY);
                }
            }
        }

        repo.shutDown();
    }
}

package it.unibz.inf.sw2016.rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

public class HttpSparqlEndpointDemo {
    public static void main(String[] args) {
        String sparqlEndpoint = "http://dblp.l3s.de/d2r/sparql";
        Repository repo = new SPARQLRepository(sparqlEndpoint);
        repo.initialize();

        try (RepositoryConnection conn = repo.getConnection()) {
            String queryString = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                    "PREFIX dc: <http://purl.org/dc/elements/1.1/>" +
                    "SELECT DISTINCT * WHERE {\n" +
                    "<http://dblp.l3s.de/d2r/resource/authors/Guohui_Xiao> foaf:name ?N .\n" +
                    "?P  dc:creator  <http://dblp.l3s.de/d2r/resource/authors/Guohui_Xiao> .\n" +
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

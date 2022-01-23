package com.bisht.graphql.springgraphql.service;

import com.bisht.graphql.springgraphql.model.Book;
import com.bisht.graphql.springgraphql.repository.BookRepository;
import com.bisht.graphql.springgraphql.service.datafetcher.AllBookDataFetcher;
import com.bisht.graphql.springgraphql.service.datafetcher.BookDataFetcher;
import com.bisht.graphql.springgraphql.service.datafetcher.CreateBookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AllBookDataFetcher allBookDataFetcher;
    @Autowired
    BookDataFetcher bookDataFetcher;
    @Autowired
    CreateBookDataFetcher createBookDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataInDB();

        File schemaFile = resource.getFile();

        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typewiring ->
                        typewiring
                                .dataFetcher("allBooks", allBookDataFetcher)
                                .dataFetcher("book", bookDataFetcher))
                .type("Mutation", typewiring ->
                        typewiring
                                .dataFetcher("createBook", createBookDataFetcher)).build();
    }

    private void loadDataInDB() {

        Stream.of(
                new Book("Never split the difference", "HardCover", "Non Fiction", new String[]{"Chris Woss"}, "Sep 2004"),
                new Book("Inner Engineering", "Paper Back", "Self Help", new String[]{"Jaggi Vasudev", "Sadhguru"}, "Aug 2009"),
                new Book("7 Habits of highly effective people", "Self Help", "genre", new String[]{"Stephen R. Covey"}, "June 1995"),
                new Book("Think like a monk", "HardCover", "Self Help", new String[]{"Jay Shetty"}, "Jan 2014"),
                new Book("Everything is fu*ked", "HardCover", "Non Fiction", new String[]{"Mark Manson"}, "Nov 2018"),
                new Book("12 Rules of Life", "Kindle edition", "Non Fiction", new String[]{"Jordan Peterson"}, "Feb 2019")
        ).forEach(bookRepository::save);
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}

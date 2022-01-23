package com.bisht.graphql.springgraphql.service.datafetcher;

import com.bisht.graphql.springgraphql.model.Book;
import com.bisht.graphql.springgraphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        Integer isn = Integer.valueOf(dataFetchingEnvironment.getArgument("id"));
        return bookRepository.getById(isn);
    }
}

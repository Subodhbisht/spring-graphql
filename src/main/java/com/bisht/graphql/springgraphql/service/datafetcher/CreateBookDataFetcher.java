package com.bisht.graphql.springgraphql.service.datafetcher;

import com.bisht.graphql.springgraphql.model.Book;
import com.bisht.graphql.springgraphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Component
public class CreateBookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {

        LinkedHashMap<String, Object> input = dataFetchingEnvironment.getArgument("input");
        String title = (String) input.get("title");
        String publisher = (String) input.get("publisher");
        String authors[] = ((List<String>) input.get("authors")).stream().toArray(String[]::new);
        Optional<String> genre = Optional.ofNullable((String) input.get("genre"));
        Optional<String> publishedDate = Optional.ofNullable((String) input.get("publishedDate"));

        Book book = new Book(title, publisher, genre.orElse("Self Help Book"), authors, publishedDate.orElse(new Date().toString()));
        return bookRepository.save(book);
    }
}

package com.bisht.graphql.springgraphql.repository;

import com.bisht.graphql.springgraphql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

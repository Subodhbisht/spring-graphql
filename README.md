# book-service

```
Backend service to search book to fetch the record from hsql dbms
```

# GraphQl queries to search all & book_by_id
```
localhost:8080/books

{
    book(id: "7"){
        title
        publisher
        authors
        publishedDate
        genre
    }
    allBooks{
        isn
        title
        publisher
        authors
        publishedDate
        genre
    }
}
```
### Query to create Book

```
mutation {
    createBook(input:{title: "Subtle art of not giving fu*k", publisher:"Paperback", authors: ["Mark Manson"], publishedDate: "Aug 2016"}){
    isn
    title
    publisher
    authors
    publishedDate
    genre
    }
}
```

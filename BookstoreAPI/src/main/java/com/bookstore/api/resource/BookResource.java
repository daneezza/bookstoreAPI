package com.bookstore.api.resource;

import com.bookstore.api.model.*;
import com.bookstore.api.exception.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.core.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    static Map<String, Book> books = new HashMap<>(); 

    @GET
    public Collection<Book> getAllBooks() {
        return books.values();
    }

    @POST
    public Response addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ISBN must not be null or empty.")
                    .build();
        }

        if (books.containsKey(book.getIsbn())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Book with ISBN " + book.getIsbn() + " already exists.")
                    .build();
        }

        books.put(book.getIsbn(), book);
        return Response.status(Response.Status.CREATED)
                .entity(book)
                .build();
    }

    @GET
    @Path("/{isbn}")
    public Book getBook(@PathParam("isbn") String isbn) {
        Book book = books.get(isbn);
        if (book == null) throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        return book;
    }

    @PUT
    @Path("/{isbn}")
    public Book updateBook(@PathParam("isbn") String isbn, Book updatedBook) {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        updatedBook.setIsbn(isbn); // Make sure ISBN stays consistent
        books.put(isbn, updatedBook);
        return updatedBook;
    }

    @DELETE
    @Path("/{isbn}")
    public void deleteBook(@PathParam("isbn") String isbn) {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        books.remove(isbn);
    }
}

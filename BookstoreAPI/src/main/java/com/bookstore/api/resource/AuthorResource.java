/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.api.resource;

import com.bookstore.api.model.*;
import com.bookstore.api.exception.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private static Map<Integer, Author> authors = new HashMap<>();
    private static int currentId = 1;

    @GET
    public Collection<Author> getAllAuthors() {
        return authors.values();
    }

    @POST
    public Author addAuthor(Author author) {
        author.setId(currentId++);
        authors.put(author.getId(), author);
        return author;
    }

    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") int id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return author;
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        if (!authors.containsKey(id)) { 
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        updatedAuthor.setId(id);
        authors.put(id, updatedAuthor);
        return updatedAuthor;
    }

    @DELETE
    @Path("/{id}")
    public void deleteAuthor(@PathParam("id") int id) {
        if (!authors.containsKey(id)) { 
            throw new AuthorNotFoundException("Author with ID " + id + " not found."); 
        }
        authors.remove(id);
    }
    
}
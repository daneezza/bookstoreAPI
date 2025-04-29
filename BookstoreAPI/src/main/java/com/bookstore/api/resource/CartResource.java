package com.bookstore.api.resource;

import com.bookstore.api.model.*;
import com.bookstore.api.exception.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    static Map<Integer, List<CartItem>> carts = new HashMap<>(); 

    // Get all items in the cart for a customer
    @GET
    public List<CartItem> getCart(@PathParam("customerId") int customerId) {
        return carts.getOrDefault(customerId, new ArrayList<>());
    }

    // Add a book to the cart for a customer
    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") int customerId, CartItem item) {
        if (item == null || item.getIsbn() == null || item.getIsbn().isEmpty()) {
            throw new BadRequestException("ISBN must not be null or empty.");
        }

        // If the customer doesn't have a cart, create one
        carts.computeIfAbsent(customerId, k -> new ArrayList<>()).add(item);
        
        return Response.status(Response.Status.CREATED)
        .entity("Item successfully added to cart for customer " + customerId + ".")
        .build();
    }

    // Update a cart item (book) quantity by ISBN
    @PUT
    @Path("/items/{isbn}")
    public void updateItem(@PathParam("customerId") int customerId,
                           @PathParam("isbn") String isbn, CartItem updatedItem) {
        List<CartItem> cart = carts.get(customerId);
        if (cart == null) throw new CartNotFoundException("Cart not found for customer " + customerId);

        // Find the book in the cart by ISBN and update the quantity
        for (CartItem item : cart) {
            if (item.getIsbn().equals(isbn)) {
                item.setQuantity(updatedItem.getQuantity());
                return;
            }
        }

        throw new BookNotFoundException("Book with ISBN " + isbn + " not found in cart.");
    }

    // Remove a book from the cart by ISBN
    @DELETE
    @Path("/items/{isbn}")
    public void removeItem(@PathParam("customerId") int customerId,
                           @PathParam("isbn") String isbn) {
        List<CartItem> cart = carts.get(customerId);
        if (cart == null) throw new CartNotFoundException("Cart not found for customer " + customerId);

        boolean removed = cart.removeIf(item -> item.getIsbn().equals(isbn));
        if (!removed) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found in cart.");
        }
    }
}
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

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private static Map<Integer, List<Order>> orders = new HashMap<>();
    private static int currentOrderId = 1;

    @GET
    public List<Order> getOrders(@PathParam("customerId") int customerId) {
        return orders.getOrDefault(customerId, new ArrayList<>());
    }

@POST
public Order placeOrder(@PathParam("customerId") int customerId) {
    // 1. Check if customer exists
    if (!CustomerResource.customers.containsKey(customerId)) {
        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
    }

    // 2. Check if cart exists and is not empty
    List<CartItem> cart = CartResource.carts.get(customerId);
    if (cart == null || cart.isEmpty()) {
        throw new CartNotFoundException("Cart is empty or not found for customer " + customerId + ".");
    }

    double total = 0;
    for (CartItem item : cart) {
        Book book = BookResource.books.get(item.getIsbn());
        
        // 3. Check if book exists
        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + item.getIsbn() + " not found.");
        }

        // 4. Check stock availability
        if (book.getStock() < item.getQuantity()) {
            throw new OutOfStockException("Not enough stock for book: " + book.getTitle());
        }

        // 5. Calculate total
        total += book.getPrice() * item.getQuantity();

        // 6. Reduce stock
        book.setStock(book.getStock() - item.getQuantity());
    }

    // 7. Create and save the order
    Order order = new Order(currentOrderId++, customerId, new ArrayList<>(cart), total);
    orders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);

    // 8. Clear the cart
    cart.clear();

    return order;
}


    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("customerId") int customerId,
                              @PathParam("orderId") int orderId) {
        List<Order> userOrders = orders.get(customerId);
        if (userOrders != null) {
            for (Order o : userOrders) {
                if (o.getId() == orderId) return o;
            }
        }
        throw new InvalidInputException("Order with ID " + orderId + " not found.");
    }
}

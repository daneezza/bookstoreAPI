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

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    static Map<Integer, Customer> customers = new HashMap<>();
    private static int currentId = 1;

    @GET
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    @POST
    public Customer addCustomer(Customer customer) {
        customer.setId(currentId++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer customer = customers.get(id);
        if (customer == null) throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        return customer;
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        if (!customers.containsKey(id)) throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        updatedCustomer.setId(id);
        customers.put(id, updatedCustomer);
        return updatedCustomer;
    }

    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        if (!customers.containsKey(id)) throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        customers.remove(id);
    }
}


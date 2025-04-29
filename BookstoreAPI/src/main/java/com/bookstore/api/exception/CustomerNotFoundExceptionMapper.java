
package com.bookstore.api.exception;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

    @Provider
    public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
        @Override
        public Response toResponse(CustomerNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Customer Not Found");
            error.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

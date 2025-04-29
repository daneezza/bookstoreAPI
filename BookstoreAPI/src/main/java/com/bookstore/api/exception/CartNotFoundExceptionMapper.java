
package com.bookstore.api.exception;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

    @Provider
    public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
        @Override
        public Response toResponse(CartNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Cart Not Found");
            error.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

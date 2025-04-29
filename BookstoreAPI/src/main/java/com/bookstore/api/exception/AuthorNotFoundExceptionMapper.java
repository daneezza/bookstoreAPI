package com.bookstore.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

    @Provider
    public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {
        @Override
        public Response toResponse(AuthorNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Author Not Found");
            error.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
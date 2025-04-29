package com.bookstore.api.exception;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

    @Provider
    public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
        @Override
        public Response toResponse(InvalidInputException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid Input");
            error.put("message", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

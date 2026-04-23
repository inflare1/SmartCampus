package com.mycompany.clientsidecw.exceptions;

import com.mycompany.clientsidecw.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception){
        ErrorMessage errorMessage = new ErrorMessage(
                "Internal Server Error",
                500,
                "An unexpected error occured"        
        );
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
    
    
}

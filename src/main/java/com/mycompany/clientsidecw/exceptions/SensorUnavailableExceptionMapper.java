package com.mycompany.clientsidecw.exceptions;

import com.mycompany.clientsidecw.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    @Override
    public Response toResponse(SensorUnavailableException exception){
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                403,
                "This sensor cannot accept new readings"        
        );
        
        return Response.status(Response.Status.FORBIDDEN)
                .entity(errorMessage)
                .build();
    }
    
    
}

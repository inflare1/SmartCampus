package com.mycompany.clientsidecw.exceptions;

import com.mycompany.clientsidecw.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException exception){
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                409,
                "Room still has sensors assigned"        
        );
        
        return Response.status(Response.Status.CONFLICT)
                .entity(errorMessage)
                .build();
    }
    
    
}

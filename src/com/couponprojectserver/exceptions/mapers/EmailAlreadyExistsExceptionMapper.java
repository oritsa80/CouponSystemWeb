package com.couponprojectserver.exceptions.mapers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponprojectserver.model.ErrorMessage;

public class EmailAlreadyExistsExceptionMapper implements ExceptionMapper<EmailAlreadyExistsException>{
	ErrorMessage message = new ErrorMessage("Email already exists in DB.", 400, "");
			//TODO: there is a problem with e - e.getMessage(), 400 , "");

	@Override
	public Response toResponse(EmailAlreadyExistsException arg0) {
		return Response.status(Status.BAD_REQUEST)
				.entity(message)
				.build();
	}
	
}

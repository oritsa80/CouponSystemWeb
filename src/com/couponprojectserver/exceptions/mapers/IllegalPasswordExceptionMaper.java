package com.couponprojectserver.exceptions.mapers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.couponproject.exception.IllegalPasswordException;
import com.couponprojectserver.model.ErrorMessage;

@Provider
public class IllegalPasswordExceptionMaper implements ExceptionMapper<IllegalPasswordException> {

	@Override
	public Response toResponse(IllegalPasswordException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), 406 , "");
		return Response.status(Status.BAD_REQUEST)
				.entity(message)
				.build();
	}

}

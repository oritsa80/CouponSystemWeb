package com.couponprojectserver.exceptions.mapers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponprojectserver.model.ErrorMessage;

public class CompanyAlreadyExistsExceptionMapper implements ExceptionMapper<CompanyAlreadyExistsException>{

	@Override
	public Response toResponse(CompanyAlreadyExistsException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), 400 , "");
		return Response.status(Status.BAD_REQUEST)
				.entity(message)
				.build();
	}
}

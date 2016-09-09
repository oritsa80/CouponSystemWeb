package com.couponprojectserver.facadeServers;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.couponproject.constants.ClientType;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.system.CouponSystem;
import com.couponprojectserver.constants.Messages;

@Path("/login")
public class LoginServer {
	
	private static final String Facade_Attr = "FACADE";
	
	@Context 
	private HttpServletRequest request;
	
	@GET
	@Path("{name}/{password}/{clientType}")
	@Produces(MediaType.TEXT_PLAIN)
	public int login (@PathParam("name") String name, 
						@PathParam("password") String password, 
						@PathParam("clientType") String clientTypeTxt) {	
		try {
			// Converting client type to enum
			ClientType clientType = ClientType.valueOf(clientTypeTxt); 
			// Checking for 
			switch(clientType){
				case Admin: 	return loginAsAdmin(name, password);
				case Company:	return loginAsCompany(name, password);
				case Customer:	return loginAsCustomer(name, password);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			//TODO: throw exception OR return Messages.FAILURE ????
		}
		return Messages.FAILURE;
	}
	
	// ADMIN
	private int loginAsAdmin(String name, String password) {
		AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(name, password);
		if (adminFacade == null) {
			return Messages.FAILURE; 
		} else {
			request.getSession().setAttribute(Facade_Attr, adminFacade);
			return Messages.SUCCESS;
		}
	}
	
	// COMPANY
	private int loginAsCompany(String name, String password) {
		CompanyFacade compFacade = CouponSystem.getInstance().loginAsCompany(name, password);
		if (compFacade == null) {
			return Messages.FAILURE;
		} else {
			request.getSession().setAttribute(Facade_Attr, compFacade);
			return Messages.SUCCESS;
		}
	}

	// CUSTOMER
	private int loginAsCustomer(String name, String password) {
		CustomerFacade custFacade = CouponSystem.getInstance().loginAsCustomer(name, password);
		if (custFacade == null) {
			return Messages.FAILURE;
		} else {
			request.getSession().setAttribute(Facade_Attr, custFacade);
			return Messages.SUCCESS;
		}
	}
}

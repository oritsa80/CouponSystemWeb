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

@Path("/login")
public class LoginServer {
	
	@Context private HttpServletRequest request;
	
	private static final String Facade_Attr = "FACADE";

	@GET
	@Path("/{name}/{password}/{clientType}")
	@Produces(MediaType.TEXT_PLAIN)
	public String login (@PathParam("name") String name, 
						@PathParam("password") String password, 
						@PathParam("clientType") ClientType clientType){
		
		switch(clientType){
			case Admin: 	return loginAsAdmin(name, password);
			case Company:	return loginAsCompany(name, password);
			case Customer:	return loginAsCustomer(name, password);
		}
		return "Failure"; 
	}
	
	private String loginAsAdmin(String name, String password) {
		AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(name, password);
		if (adminFacade == null) {
			return "Failure"; 
		} else {
			request.getSession().setAttribute(Facade_Attr, adminFacade);
			return "success";
		}
	}

	private String loginAsCompany(String name, String password) {
		CompanyFacade compFacade = CouponSystem.getInstance().loginAsCompany(name, password);
		if (compFacade == null) {
			return "Failure";
		} else {
			request.getSession().setAttribute(Facade_Attr, compFacade);
			return "success";
		}

	}

	private String loginAsCustomer(String name, String password) {
		CustomerFacade custFacade = CouponSystem.getInstance().loginAsCustomer(name, password);
		if (custFacade == null) {
			return "Failure";
		} else {
			request.getSession().setAttribute(Facade_Attr, custFacade);
			return "success";
		}
	}
}

package com.couponprojectserver.facadeServers;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.couponproject.constants.ClientType;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.system.CouponSystem;
import com.couponprojectserver.exceptions.WrongClientTypeException;

@Path("/login")
public class LoginServer {
	
	private static final String Facade_Attr = "FACADE";
	
	@Context 
	private HttpServletRequest request;
	
	public void login (@PathParam("name") String name, @PathParam("password") String password, 
			@PathParam("clientType") ClientType clientType){
		switch(clientType){
			case Admin: 
				loginAsAdmin(name, password);
				break;
			case Company:
				loginAsCompany(name, password);
				break;	
			case Customer:
				loginAsCustomer(name, password);
				break;
		}
	}

	private void loginAsAdmin(String name, String password) {
		AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(name, password);
		request.getSession().setAttribute(Facade_Attr, adminFacade);
		//TODO: cookies
		//TODO: 
	}

	private void loginAsCompany(String name, String password) {
		CompanyFacade compFacade = CouponSystem.getInstance().loginAsCompany(name, password);
		request.getSession().setAttribute(Facade_Attr, compFacade);
		//TODO: cookies
	}
	
	private void loginAsCustomer(String name, String password) {
		CustomerFacade custFacade = CouponSystem.getInstance().loginAsCustomer(name, password);
		request.getSession().setAttribute(Facade_Attr, custFacade);
		//TODO: cookies
	}

}

package com.couponprojectserver.facadeServers;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

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
	
	public void login(@PathParam("name") String name, @PathParam("password") String password, 
			@PathParam("clientType") String clientType) throws WrongClientTypeException{
		//TODO: enum??
		if(clientType.equals("admin")){
			AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(name, password);
			request.getSession().setAttribute(Facade_Attr, adminFacade);
		}else{
			if(clientType.equals("company")){
				CompanyFacade compFacade = CouponSystem.getInstance().loginAsCompany(name, password);
				request.getSession().setAttribute(Facade_Attr, compFacade);
			}else{
				if(clientType.equals("customer")){
					CustomerFacade custFacade = CouponSystem.getInstance().loginAsCustomer(name, password);
					request.getSession().setAttribute(Facade_Attr, custFacade);
				}
				else throw new WrongClientTypeException("The Client Type should be admin, company or custoemr");
			}
		}
	}

}

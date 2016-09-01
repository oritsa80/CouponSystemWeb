package com.couponprojectserver.facadeServers;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;

import com.couponproject.beans.Coupon;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.facade.CompanyFacade;
import com.couponprojectserver.exceptions.CompanyFacadeServerException;
import com.couponprojectserver.exceptions.EmptyCoupinException;

@Path("/CompanyService")
public class CompanyFacadeServer {
	
	//to check this thing - when I am entering the attribute to the session. maybe to create first a login server and see...
	private static final String Facade_Attr = "FACADE";
	@Context 
	private HttpServletRequest request;
		
	//login
	
	//createCoupon(Coupon coupon)
	@PUT
	@Path("/createCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCoupon(@PathParam("coupon") Coupon coupon) throws CompanyFacadeServerException, EmptyCoupinException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the coupon sent is not empty
		if(coupon==null){
			throw new EmptyCoupinException("Coupon details are missing exception");
		}
		//the createCoupon function
		try {
			compFacade.createCoupon(coupon);
		} catch (CompanyFacadeException | CouponTitleAlreadyExistException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "createCoupon() Error: " + e.getMessage(), e);
		}
	}
	//removeCoupon(Coupon coupon)
	//updateCoupon(Coupon coupon)
	//getCoupon(long id)
	//getUniqueCouponTypes()
	//getAllCoupons()
	//getCouponByType(CouponType type) 
	//getCouponByPrice(double price)
	//getCouponByStartDate(LocalDate date)
	//getCouponByEndDate(LocalDate date)
}

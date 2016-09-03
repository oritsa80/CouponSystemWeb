package com.couponprojectserver.facadeServers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponAlreadyPurchasedException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.exception.OutOfDateException;
import com.couponproject.exception.OutOfStockException;
import com.couponproject.facade.CustomerFacade;
import com.couponprojectserver.exceptions.CustomerFacadeServerException;
import com.couponprojectserver.exceptions.emptyPriceException;
import com.couponprojectserver.exceptions.emptyTypeException;

@Path("/CustomerService")
public class CustomerFacadeServer {

	private static final String Facade_Attr = "FACADE";//TODO: check this
	@Context 
	private HttpServletRequest request;
	
	//login??
	//Collection<CouponType> getUniqueCouponTypes()
	@GET
	@Path("/getUniqueCouponTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CouponType> getUniqueCouponTypes() throws CustomerFacadeServerException{
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// The getUniqueCouponTypes function
		try {
			return custFacade.getUniqueCouponTypes();
		} catch (CouponSystemException e) {
			throw new CustomerFacadeServerException("CustomerFacadeServerException - "
					+ "getUniqueCouponTypes() Error: " + e.getMessage(), e);
		}
	}
	
	//purchaseCoupon(long id)
	@POST
	@Path("/purchaseCoupon/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void purchaseCoupon(@PathParam("id") long id) throws CustomerFacadeServerException{
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// The purchaseCoupon function	
		try {
			custFacade.purchaseCoupon(id);
		} catch (CouponAlreadyPurchasedException | OutOfStockException | OutOfDateException | CouponSystemException e) {
			throw new CustomerFacadeServerException("CustomerFacadeServerException - "
					+ "purchaseCoupon() Error: " + e.getMessage(), e);
		}
	}
	
	//Collection<Coupon> getAllPurchasedCoupons()
	@GET
	@Path("/getAllPurchasedCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerFacadeServerException{
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// The getAllPurchasedCoupons function	
		try {
			return custFacade.getAllPurchasedCoupons();
		} catch (CustomerFacadeException e) {
			throw new CustomerFacadeServerException("CustomerFacadeServerException - "
					+ "purchaseCoupon() Error: " + e.getMessage(), e);
		}
	}
	
	//Collection<Coupon> getAllPurchasedCouponsByType(CouponType type)
	@GET
	@Path("/getAllPurchasedCouponsByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByType(@PathParam("type") CouponType type) throws emptyTypeException, CustomerFacadeServerException{
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		//check if type is not null
		if(type==null){
			throw new emptyTypeException("No Type received Error");
		}
		//the getAllPurchasedCouponsByType function
		try {
			return custFacade.getAllPurchasedCouponsByType(type);
		} catch (CustomerFacadeException e) {
			throw new CustomerFacadeServerException("CustomerFacadeServerException - "
					+ "getAllPurchasedCouponsByType() Error: " + e.getMessage(), e);
		}
	}
	
	//Collection<Coupon> getAllPurchasedCouponsByPrice(Double price)
	@GET
	@Path("/getAllPurchasedCouponsByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@PathParam("price") Double price) throws 
															emptyPriceException, CustomerFacadeServerException{
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		//check if price is not null
		if(price==null){
			throw new emptyPriceException("No Price received Error");
		}
		//the getAllPurchasedCouponsByPrice function
		try {
			return custFacade.getAllPurchasedCouponsByPrice(price);
		} catch (CustomerFacadeException e) {
			throw new CustomerFacadeServerException("CustomerFacadeServerException - "
					+ "getAllPurchasedCouponsByType() Error: " + e.getMessage(), e);
		}
	}
	
}

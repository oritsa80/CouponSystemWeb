package com.couponprojectserver.facadeServers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.eclipse.persistence.platform.database.oracle.annotations.NamedPLSQLStoredProcedureQueries;

import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
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
		
	//login??
	
	//createCoupon(Coupon coupon)
	@POST
	@Path("/createCoupon/{coupon}")
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
	@DELETE
	@Path("/removeCoupon/{coupon}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCoupon(@PathParam("coupon") Coupon coupon) throws EmptyCoupinException, CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the coupon sent is not empty
		if(coupon==null){
			throw new EmptyCoupinException("Coupon details are missing exception");
		}
		//the removeCoupon function
		try {
			compFacade.removeCoupon(coupon);
		} catch (CompanyFacadeException | CouponDoesNotExistException | CompanyCouponDoesNotExistsException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "removeCoupon() Error: " + e.getMessage(), e);
		}
	}
	
	//updateCoupon(Coupon coupon)
	@POST
	@Path("/updateCoupon/{coupon}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCoupon(@PathParam("coupon") Coupon coupon) throws EmptyCoupinException, CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the coupon sent is not empty
		if(coupon==null){
			throw new EmptyCoupinException("Coupon details are missing exception");
		}
		//the updateCoupon function
		try {
			compFacade.updateCoupon(coupon);
		} catch (CompanyFacadeException | CouponTitleAlreadyExistException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "updateCoupon() Error: " + e.getMessage(), e);
		}
	}
	
	//getCoupon(long id)
	@GET
	@Path("/getCoupon/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon getCoupon(@PathParam("id") long id) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouopn function
		try {
			return compFacade.getCoupon(id);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCoupon() Error: " + e.getMessage(), e);
		}
	}
	
	//getUniqueCouponTypes()
	@GET
	@Path("/UniqueCouponTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CouponType> getUniqueCouponTypes() throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getUniqueCouponTypes function
		try {
			return compFacade.getUniqueCouponTypes();
		} catch (CouponSystemException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getUniqueCouponTypes() Error: " + e.getMessage(), e);
		}
	}
	
	//getAllCoupons()
	@GET
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupons() throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getAllCoupons function
		try {
			return compFacade.getAllCoupons();
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getAllCoupons() Error: " + e.getMessage(), e);
		}
	}
	
	//getCouponByType(CouponType type)
	@GET
	@Path("/getCouponByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByType(@PathParam("type") CouponType type) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouponByType function
		try {
			return compFacade.getCouponByType(type);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByType() Error: " + e.getMessage(), e);
		}
	}
	
	//getCouponByPrice(double price)
	@GET
	@Path("/getCouponByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByPrice(@PathParam("price") double price) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouponByPrice function
		try {
			return compFacade.getCouponByPrice(price);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByPrice() Error: " + e.getMessage(), e);
		}
	}
	
	//getCouponByStartDate(LocalDate date)
	@GET
	@Path("/getCouponByStartDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByStartDate(@PathParam("date") LocalDate date) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouponByStartDate function
		try {
			return compFacade.getCouponByStartDate(date);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByStartDate() Error: " + e.getMessage(), e);
		}
	}
	//getCouponByEndDate(LocalDate date)
	@GET
	@Path("/getCouponByEndDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByEndDate(@PathParam("date") LocalDate date) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouponByStartDate function
		try {
			return compFacade.getCouponByEndDate(date);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByEndDate() Error: " + e.getMessage(), e);
		}
	}
}

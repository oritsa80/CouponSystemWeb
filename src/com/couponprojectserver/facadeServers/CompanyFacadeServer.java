package com.couponprojectserver.facadeServers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.couponprojectserver.exceptions.EmptyCouponException;

@Path("/CompanyService")
public class CompanyFacadeServer {
	
	//to check this thing - when I am entering the attribute to the session. maybe to create first a login server and see...
	private static final String Facade_Attr = "FACADE";
	@Context 
	private HttpServletRequest request;
		
	//login??
	
	//createCoupon
	@POST
	@Path("/createCoupon/{coup_title}/{startDate}/{endDate}/{amount}/{type}/{message}/{price}/{image}")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCoupon(@PathParam("coup_title") String coup_title,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("amount") int amount,
			@PathParam("type") String type,
			@PathParam("message") String message,
			@PathParam("price") double price,
			@PathParam("image") String image) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the coupon sent is not empty
		//creating new coupon instance
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		CouponType coupoType = CouponType.valueOf(type);
		Coupon coupon = new Coupon(coup_title, startLocalDate, endLocalDate, amount, coupoType, message, price, image);
		//the createCoupon function
		try {
			compFacade.createCoupon(coupon);
		} 
		catch (CompanyFacadeException compE){
			//TODO:
		}
		catch (CouponTitleAlreadyExistException coupE){
			//TODO:
		}
	}
	
	//removeCoupon
	@DELETE
	@Path("/removeCoupon/{coup_id}/{coup_title}/{startDate}/{endDate}/{amount}/{type}/{message}/{price}/{image}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCoupon(@PathParam("coup_id") int coup_id,
			@PathParam("coup_title") String coup_title,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("amount") int amount,
			@PathParam("type") String type,
			@PathParam("message") String message,
			@PathParam("price") double price,
			@PathParam("image") String image){
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the coupon sent is not empty
		//creating new coupon instance
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		CouponType coupoType = CouponType.valueOf(type);
		Coupon coupon = new Coupon(coup_id, coup_title, startLocalDate, endLocalDate, amount, coupoType, message, price, image);
		//the removeCoupon function
		try {
			compFacade.removeCoupon(coupon);
		} 
		catch (CompanyFacadeException compE){
			//TODO:
		}
		catch (CouponDoesNotExistException coupE){
			//TODO:
		}
		catch (CompanyCouponDoesNotExistsException compCoupE){
			//TODO:
		}
	}
	
	//updateCoupon
	@POST
	@Path("/updateCoupon/{coup_id}/{coup_title}/{startDate}/{endDate}/{amount}/{type}/{message}/{price}/{image}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCoupon(@PathParam("coup_id") int coup_id,
			@PathParam("coup_title") String coup_title,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("amount") int amount,
			@PathParam("type") String type,
			@PathParam("message") String message,
			@PathParam("price") double price,
			@PathParam("image") String image){
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the coupon sent is not empty
		//creating new coupon instance
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		CouponType coupoType = CouponType.valueOf(type);
		Coupon coupon = new Coupon(coup_id, coup_title, startLocalDate, endLocalDate, amount, coupoType, message, price, image);		
		//the updateCoupon function
		try {
			compFacade.updateCoupon(coupon);
		}
		catch (CompanyFacadeException compE){
			//TODO:
		}
		catch (CouponTitleAlreadyExistException coupE){
			//TODO:
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
	
	//getCouponByType
	@GET
	@Path("/getCouponByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByType(@PathParam("type") String type) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		CouponType coupoType = CouponType.valueOf(type);
		//the getCouponByType function
		try {
			return compFacade.getCouponByType(coupoType);
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
	
	//getCouponByStartDate
	@GET
	@Path("/getCouponByStartDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByStartDate(@PathParam("date") String startDate) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		LocalDate startLocalDate = LocalDate.parse(startDate);
		//the getCouponByStartDate function
		try {
			return compFacade.getCouponByStartDate(startLocalDate);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByStartDate() Error: " + e.getMessage(), e);
		}
	}
	//getCouponByEndDate
	@GET
	@Path("/getCouponByEndDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByEndDate(@PathParam("date") String endDate) throws CompanyFacadeServerException{
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		//the getCouponByStartDate function
		try {
			return compFacade.getCouponByEndDate(endLocalDate);
		} catch (CompanyFacadeException e) {
			throw new CompanyFacadeServerException("CompanyFacadeServerException - "
					+ "getCouponByEndDate() Error: " + e.getMessage(), e);
		}
	}
}

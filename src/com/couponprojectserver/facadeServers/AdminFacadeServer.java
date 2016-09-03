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

import com.couponproject.beans.Company;
import com.couponproject.beans.Customer;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;
import com.couponprojectserver.exceptions.AdminFacadeServerException;
import com.couponprojectserver.exceptions.EmptyCompanyException;
import com.couponprojectserver.exceptions.EmptyCustomerException;

@Path("/AdminService")
public class AdminFacadeServer {
	
	private static final String Facade_Attr = "FACADE";
	@Context 
	private HttpServletRequest request;
	
	//login??
	
	// ***************
	// Company methods
	// ***************
	
	//createCompany(Company company)
	@POST
	@Path("/createCompany/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCompany(@PathParam("company") Company company) throws EmptyCompanyException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the company sent is not empty
		if(company==null){
			throw new EmptyCompanyException("Company details are missing Error");
		}
		//the createCompany function
		try {
			adminFacade.createCompany(company);
		} catch (AdminFacadeException | IllegalPasswordException | EmailAlreadyExistsException
				| CompanyAlreadyExistsException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "createCompany() Error: " + e.getMessage(), e);
		}
	}
	
	//removeCompany(Company company)
	@POST
	@Path("/removeCompany/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCompany(@PathParam("company") Company company) throws EmptyCompanyException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the coupon sent is not empty
		if(company==null){
			throw new EmptyCompanyException("Company details are missing Error");
		}
		//the removeCompany function
		try {
			adminFacade.removeCompany(company);
		} catch (AdminFacadeException | CouponDoesNotExistException | CompanyDoesNotExistException
				| CompanyCouponDoesNotExistsException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "removeCompany() Error: " + e.getMessage(), e);
		}
	}
	
	//updateCompany(Company company)
	@POST
	@Path("/updateCompany/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCompany(@PathParam("company") Company company) throws EmptyCompanyException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the coupon sent is not empty
		if(company==null){
			throw new EmptyCompanyException("Company details are missing Error");
		}
		//the removeCompany function
		try {
			adminFacade.updateCompany(company);
		} catch (AdminFacadeException | IllegalPasswordException | EmailAlreadyExistsException
				| CompanyAlreadyExistsException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "updateCompany() Error: " + e.getMessage(), e);
		}
	}
	
	//Company getCompany(long id)
	@GET
	@Path("/getCompany")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(@PathParam("id") long id) throws AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCompany function
		try {
			return adminFacade.getCompany(id);
		} catch (AdminFacadeException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "getCompany() Error: " + e.getMessage(), e);
		}
	}
	
	//Collection<Company> getAllCompanies()
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompanies() throws AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//the getAllCompanies function
		try {
			return adminFacade.getAllCompanies();
		} catch (AdminFacadeException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "getAllCompanies() Error: " + e.getMessage(), e);
		}
	}
	
	// ***************
	// Customer method
	// ***************	
	
	//createCustomer(Customer customer)
	@POST
	@Path("/createCustomer/{customer}")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCustomer(@PathParam("customer") Customer customer) throws EmptyCustomerException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the customer sent is not empty
		if(customer==null){
			throw new EmptyCustomerException("Customer details are missing Error");
		}
		//the createCustomer function
		try {
			adminFacade.createCustomer(customer);
		} catch (AdminFacadeException | IllegalPasswordException | CustomerAlreadyExistsException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "createCustomer() Error: " + e.getMessage(), e);
		}
	}
	
	//removeCustomer(Customer customer)
	@POST
	@Path("/removeCustomer/{customer}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCustomer(@PathParam("customer") Customer customer) throws EmptyCustomerException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the customer sent is not empty
		if(customer==null){
			throw new EmptyCustomerException("Customer details are missing Error");
		}
		//the createCustomer function
		try {
			adminFacade.removeCustomer(customer);
		} catch (AdminFacadeException | CouponDoesNotExistException | CustomerDoesNotExistException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "removeCustomer() Error: " + e.getMessage(), e);
		}
	}
	
	//updateCustomer(Customer customer)
	@POST
	@Path("/updateCustomer/{customer}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCustomer(@PathParam("Customer") Customer customer) throws EmptyCustomerException, AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//Verifying that the customer sent is not empty
		if(customer==null){
			throw new EmptyCustomerException("Customer details are missing Error");
		}
		//the updateCustomer function
		try {
			adminFacade.updateCustomer(customer);
		} catch (AdminFacadeException | IllegalPasswordException | CustomerAlreadyExistsException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "updateCustomer() Error: " + e.getMessage(), e);
		}
	}
	
	//Customer getCustomer(long id)
	@GET
	@Path("/getCustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") long id) throws AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCustomer function
		try {
			return adminFacade.getCustomer(id);
		} catch (AdminFacadeException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "getCustomer() Error: " + e.getMessage(), e);
		}
	}
	
	//Collection<Customer> getAllCustomers()
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getAllCustomers() throws AdminFacadeServerException{
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//the getAllCustomers function
		try {
			return adminFacade.getAllCustomers();
		} catch (AdminFacadeException e) {
			throw new AdminFacadeServerException("AdminFacadeServerException - "
					+ "getAllCustomers() Error: " + e.getMessage(), e);
		}
	}
	
}

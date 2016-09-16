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
import com.couponproject.exception.*;
import com.couponproject.facade.AdminFacade;
import com.couponprojectserver.exceptions.AdminFacadeServerException;
import com.couponprojectserver.exceptions.EmptyCompanyException;
import com.couponprojectserver.exceptions.EmptyCustomerException;

@Path("/AdminService")
public class AdminFacadeServer {
	
	private static final String Facade_Attr = "FACADE";
	
	@Context 
	private HttpServletRequest request;
	
	// ***************
	// Company methods
	// ***************
	
	//Create company
	@POST
	@Path("createCompany/{name}/{password}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCompany(@PathParam("name") String name, 
			@PathParam("password") String password,
			@PathParam("email") String email){
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		
		try {
			Company company = new Company(name, password, email);
			adminFacade.createCompany(company);
		} catch (AdminFacadeException adminE) {
			// TODO:
		} catch (IllegalPasswordException passE) {
			// TODO:			
		} catch (CompanyAlreadyExistsException EE) {
			// TODO:
		} catch (EmailAlreadyExistsException mailE) {
			// TODO:
		}
	}
	
	//removeCompany
	@POST
	@Path("/removeCompany/{comp_id}/{comp_name}/{comp_password}/{comp_email}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCompany(@PathParam("comp_id") int comp_id, 
			@PathParam("comp_name") String comp_name, 
			@PathParam("comp_password") String comp_password, 
			@PathParam("comp_email") String comp_email){
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the company sent is not empty
		//creating new company instance
		Company company = new Company(comp_id, comp_name, comp_password, comp_email);
		//the removeCompany function
		try {
			adminFacade.removeCompany(company);
		} 
		catch (AdminFacadeException adminE){
			//TODO:
		}
		catch (CouponDoesNotExistException coupE){
			//TODO:
		}
		catch (CompanyDoesNotExistException compE){
			//TODO:
		}
		catch (CompanyCouponDoesNotExistsException compCoupE){
			//TODO:
		}
	}
	
	//updateCompany
	@POST
	@Path("/updateCompany/{comp_id}/{comp_name}/{comp_password}/{comp_email}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCompany(@PathParam("comp_id") int comp_id, 
			@PathParam("comp_name") String comp_name, 
			@PathParam("comp_password") String comp_password, 
			@PathParam("comp_email") String comp_email) {
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the company sent is not empty
		//creating new company instance
		Company company = new Company(comp_id, comp_name, comp_password, comp_email);
		//the removeCompany function
		try {
			adminFacade.updateCompany(company);
		} 
		catch (AdminFacadeException adminE){
			//TODO:
		}
		catch (IllegalPasswordException passE){
			//TODO:
		}
		catch (EmailAlreadyExistsException emailE){
			//TODO:
		}
		catch (CompanyAlreadyExistsException compE){
			//TODO:
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
	
	//createCustomer
	@POST
	@Path("/createCustomer/{cust_name}/{cust_password}")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCustomer(@PathParam("cust_name") String cust_name,
			@PathParam("cust_password") String cust_password){
		
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the customer sent is not empty
		//creating new company instance
		Customer customer = new Customer(cust_name, cust_password);	
		//the createCustomer function
		try {
			adminFacade.createCustomer(customer);
		} 
		catch (AdminFacadeException adminE){
			//TODO:
		}
		catch (IllegalPasswordException passE){
			//TODO:
		}
		catch (CustomerAlreadyExistsException custE){
			//TODO:
		}
	}
	
	//removeCustomer
	@POST
	@Path("/removeCustomer/{cust_id}/{cust_name}/{cust_password}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeCustomer(@PathParam("cust_is") int cust_id,
			@PathParam("cust_name") String cust_name,
			@PathParam("cust_password") String cust_password){
		
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the customer sent is not empty
		//creating new company instance
		Customer customer = new Customer(cust_id, cust_name, cust_password);
		//the createCustomer function
		try {
			adminFacade.removeCustomer(customer);
		} 
		catch (AdminFacadeException adminE){
			//TODO:
		}
		catch (CouponDoesNotExistException coupE){
			//TODO:
		}
		catch (CustomerDoesNotExistException custE){
			//TODO:
		}
	}
	
	//updateCustomer(Customer customer)
	@POST
	@Path("/updateCustomer/{cust_id}/{cust_name}/{cust_password}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCustomer(@PathParam("cust_is") int cust_id,
			@PathParam("cust_name") String cust_name,
			@PathParam("cust_password") String cust_password){
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		//TODO: Verifying that the customer sent is not empty
		//creating new company instance
		Customer customer = new Customer(cust_id, cust_name, cust_password);
		//the updateCustomer function
		try {
			adminFacade.updateCustomer(customer);
		} 
		catch (AdminFacadeException adminE){
			//TODO:
		}
		catch (IllegalPasswordException passE){
			//TODO:
		}
		catch (CustomerAlreadyExistsException custE){
			//TODO:
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

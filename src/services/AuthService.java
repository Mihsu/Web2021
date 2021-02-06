package services;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Admin;
import Model.Customer;
import Model.Seller;
import Model.User;
import collections.Admins;
import collections.Customers;
import collections.Sellers;
import dto.LoginUserDTO;
import dto.RegisterUserDTO;
import dto.UserDTO;
import enums.CustomerTypeNames;
import enums.Genders;
import enums.Roles;

@Path("/auth")
public class AuthService {
	
	@Context
	HttpServletRequest req;
	@Context
	ServletContext ctx;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(LoginUserDTO dto) {
		Admin a;
		Seller s;
		Customer c;
		if ((a = tryLoginAdmin(dto.getUsername(), dto.getPassword())) != null) {

			return a.getUsername() + ",admin";
		}
		else if ((c = tryLoginCustomer(dto.getUsername(), dto.getPassword())) != null) {
			return c.getUsername() + ",customer";
		}
		else if ((s = tryLoginSeller(dto.getUsername(), dto.getPassword())) != null) {

			return s.getUsername()+ ",seller";
		}
		return "NOT_FOUND";

	}
	
	@GET
	@Path("/loggedIn/{username}/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO loggedIn(@PathParam("username") String username, @PathParam("role") String role) {
		if(role.equalsIgnoreCase("admin")) {
			Admins admins = new Admins(ctx.getRealPath("."));
			if (admins.getAdmins().containsKey(username)) {
				Admin admin = admins.getAdmins().get(username);
				return new UserDTO(admin.getUsername(), admin.getFirstName(), admin.getLastName(),admin.getGender().toString(), admin.getBirthDate());
			}
		}else if(role.equalsIgnoreCase("customer")){
			Customers customers = new Customers(ctx.getRealPath("."));
			if (customers.getCustomers().containsKey(username)) {
				Customer customer = customers.getCustomers().get(username);
				return new UserDTO(customer.getUsername(), customer.getFirstName(), customer.getLastName(),customer.getGender().toString(), customer.getBirthDate(), customer.getCollectedPoints());
			}
		}
		else if(role.equalsIgnoreCase("seller")){
			Sellers sellers = new Sellers(ctx.getRealPath("."));
			if (sellers.getSellers().containsKey(username)) {
				Seller seller = sellers.getSellers().get(username);
				return new UserDTO(seller.getUsername(), seller.getFirstName(), seller.getLastName(),seller.getGender().toString(), seller.getBirthDate());
			}
		}
		return null;
		
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(RegisterUserDTO newUser) {
		Customers customers = new Customers(ctx.getRealPath("."));
		Admins admins = new Admins(ctx.getRealPath("."));
		Sellers sellers = new Sellers(ctx.getRealPath("."));
		if (customers.getCustomers().containsKey(newUser.getUsername())) {
			return "ALREADYEXISTS";
	    }
		else if (admins.getAdmins().containsKey(newUser.getUsername())) {
			return "ALREADYEXISTS";
		}else if(sellers.getSellers().containsKey(newUser.getUsername())){
			return "ALREADYEXISTS";
		}
		else {
			
			Customer newCustomer = registerDtoToCustomer(newUser);
			newCustomer.setCollectedPoints(0);
			newCustomer.setCustomerTypeName(CustomerTypeNames.SILVER);
			
			customers.getCustomers().put(newCustomer.getUsername(), newCustomer);
			customers.save(ctx.getRealPath("."));
			return "OK";
		}
	}
	

	
	
	private Customer registerDtoToCustomer(RegisterUserDTO newUser) {
		Customer cust = new Customer();
		cust.setActive(true);
		cust.setBirthDate(newUser.getBirthDate());
		cust.setFirstName(newUser.getFirstName());
		cust.setLastName(newUser.getLastName());
		if(newUser.getGender().equalsIgnoreCase("MALE"))
		{
			cust.setGender(Genders.MALE);
		}else {
			cust.setGender(Genders.FEMALE);
		}
		cust.setRole(Roles.CUSTOMER);
		cust.setPassword(newUser.getPassword());
		cust.setUsername(newUser.getUsername());
		return cust;
	}

	@POST
	@Path("/logout")
	public void logout() {
		req.getSession().invalidate();
	}
	
	private Customer tryLoginCustomer(String username, String password) {
		Customers customers = new Customers(ctx.getRealPath("."));
		
		if (customers.getCustomers().containsKey(username)) {
			
			Customer customer = customers.getCustomers().get(username);
			if (customer.getPassword().equals(password)) {
				if (customer.isActive()) {
					return customer;
				} else {
					return null;
				}
			}
		}

		return null;
	}
	
	private Admin tryLoginAdmin(String username, String password) {
		Admins admins = new Admins(ctx.getRealPath("."));
		
		if (admins.getAdmins().containsKey(username)) {
			Admin admin = admins.getAdmins().get(username);
			if (admin.getPassword().equals(password)) {
				req.getSession(true).setAttribute("admin", admin);
				return admin;
			} else {
				return null;
			}
			
		}
		return null;
	}
	
	private Seller tryLoginSeller(String username, String password) {
		Sellers sellers = new Sellers(ctx.getRealPath("."));
		
		if (sellers.getSellers().containsKey(username)) {
			Seller seller = sellers.getSellers().get(username);
			if (seller.getPassword().equals(password)) {
				req.getSession().setAttribute("seller", seller);
				return seller;
			} else {
				return null;
			}
			
		}
		return null;
	}
}
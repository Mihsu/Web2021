package services;



import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Enums.CustomerTypeName;
import Enums.Gender;
import Enums.Role;
import Model.Admin;
import Model.Customer;
import Model.Seller;
import Model.User;
import collections.Admins;
import collections.Customers;
import collections.Sellers;
import dto.LoginUserDTO;
import dto.RegisterUserDTO;

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
		if (tryLoginAdmin(dto.getUsername(), dto.getPassword()))
			return "admin";
		else if (tryLoginCustomer(dto.getUsername(), dto.getPassword()).equalsIgnoreCase("ok")) {
			return "user";
		}
		else if (tryLoginCustomer(dto.getUsername(), dto.getPassword()).equalsIgnoreCase("blocked")) {

			return "blocked";
		}
		else if (tryLoginSeller(dto.getUsername(), dto.getPassword())) {

			return "seller";
		}

		return "none";
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean register(RegisterUserDTO newUser) {
		Customers customers = new Customers(ctx.getRealPath("."));
		Admins admins = new Admins(ctx.getRealPath("."));

		if (customers.getCustomers().containsKey(newUser.getUsername())) {
			return false;
	    }
		else if (admins.getAdmins().containsKey(newUser.getUsername())) {
			return false;
		}
		else {
			
			Customer newCustomer = registerDtoToCustomer(newUser);
			newCustomer.setCollectedPoints(0);
			newCustomer.setCustomerTypeName(CustomerTypeName.SILVER);
			
			customers.getCustomers().put(newCustomer.getUsername(), newCustomer);
			customers.save(ctx.getRealPath("."));
			return true;
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
			cust.setGender(Gender.MALE);
		}else {
			cust.setGender(Gender.FEMALE);
		}
		cust.setRole(Role.CUSTOMER);
		cust.setPassword(newUser.getPassword());
		cust.setUsername(newUser.getUsername());
		return cust;
	}

	@POST
	@Path("/logout")
	public void logout() {
		req.getSession().invalidate();
	}
	
	
	@GET
	@Path("/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getLoggedIn() {
		if (req.getSession().getAttribute("admin") != null) {
			return req.getSession().getAttribute("admin");
		} else if (req.getSession().getAttribute("customer") != null) {
			return req.getSession().getAttribute("customer");
		} else if (req.getSession().getAttribute("seller") != null) {
			return req.getSession().getAttribute("seller");
		} else {
			return null;
		}
	}
	
	private String tryLoginCustomer(String username, String password) {
		Customers customers = new Customers(ctx.getRealPath("."));
		
		if (customers.getCustomers().containsKey(username)) {
			
			Customer customer = customers.getCustomers().get(username);
			System.out.println(customer);
			if (customer.getPassword().equals(password)) {
				if (customer.isActive()) {
					req.getSession().setAttribute("customer", customer);
					return "ok";
				} else {
					return "blocked";
				}
			}
		}

		return "false";
	}
	
	private boolean tryLoginAdmin(String username, String password) {
		Admins admins = new Admins(ctx.getRealPath("."));
		
		if (admins.getAdmins().containsKey(username)) {
			Admin admin = admins.getAdmins().get(username);
			if (admin.getPassword().equals(password)) {
				req.getSession().setAttribute("admin", admin);
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}
	
	private boolean tryLoginSeller(String username, String password) {
		Sellers sellers = new Sellers(ctx.getRealPath("."));
		
		if (sellers.getSellers().containsKey(username)) {
			Seller seller = sellers.getSellers().get(username);
			if (seller.getPassword().equals(password)) {
				req.getSession().setAttribute("seller", seller);
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}
}
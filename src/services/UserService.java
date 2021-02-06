package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Admin;
import Model.Customer;
import Model.CustomerType;
import Model.Seller;
import collections.Admins;
import collections.Customers;
import collections.Sellers;
import dto.CommentDbDTO;
import dto.RegisterUserDTO;
import dto.UserDTO;
import dto.UsersDTO;
import enums.CustomerTypeNames;
import enums.Genders;
import enums.Roles;


@Path("/user")
public class UserService {

	@Context
	ServletContext ctx;
	@Context
	HttpServletRequest req;
	
	@POST
	@Path("/update/{role}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInfo(@PathParam("role") String role, UserDTO changedUser) {
		if(role.equalsIgnoreCase("admin")) {
			Admins admins = new Admins(ctx.getRealPath("."));
			if (admins.getAdmins().containsKey(changedUser.getUsername())) {
				admins.getAdmins().get(changedUser.getUsername()).setFirstName(changedUser.getFirstName());
				admins.getAdmins().get(changedUser.getUsername()).setLastName(changedUser.getLastName());
				admins.save(ctx.getRealPath("."));
				return "OK";			
				}

		}else if(role.equalsIgnoreCase("customer")){
			Customers customers = new Customers(ctx.getRealPath("."));
			if (customers.getCustomers().containsKey(changedUser.getUsername())) {
				customers.getCustomers().get(changedUser.getUsername()).setFirstName(changedUser.getFirstName());
				customers.getCustomers().get(changedUser.getUsername()).setLastName(changedUser.getLastName());
				customers.save(ctx.getRealPath("."));
				return "OK";	
			}
		
		}
		else if(role.equalsIgnoreCase("seller")){
			Sellers sellers = new Sellers(ctx.getRealPath("."));
			if (sellers.getSellers().containsKey(changedUser.getUsername())) {
				sellers.getSellers().get(changedUser.getUsername()).setFirstName(changedUser.getFirstName());
				sellers.getSellers().get(changedUser.getUsername()).setLastName(changedUser.getLastName());
				sellers.save(ctx.getRealPath("."));

				return "OK";	
			}
		}
		return "NOT_FOUND";
	}
	
	@POST
	@Path("/addseller")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addSeller(RegisterUserDTO newUser) {
		Customers customers = new Customers(ctx.getRealPath("."));
		Sellers sellers = new Sellers(ctx.getRealPath("."));
		Admins admins = new Admins(ctx.getRealPath("."));

		if (customers.getCustomers().containsKey(newUser.getUsername())) {
			return "ALREADYEXISTS";
	    }
		else if (admins.getAdmins().containsKey(newUser.getUsername())) {
			return "ALREADYEXISTS";
		}else if(sellers.getSellers().containsKey(newUser.getUsername())){
			return "ALREADYEXISTS";
		}
		else {
			
			Seller newSeller = registerDtoToSeller(newUser);
			
			sellers.getSellers().put(newSeller.getUsername(), newSeller);
			sellers.save(ctx.getRealPath("."));
			return "OK";
		}
	}
	private Seller registerDtoToSeller(RegisterUserDTO newUser) {
		Seller seller = new Seller();
		seller.setActive(true);
		seller.setBirthDate(newUser.getBirthDate());
		seller.setFirstName(newUser.getFirstName());
		seller.setLastName(newUser.getLastName());
		if(newUser.getGender().equalsIgnoreCase("MALE"))
		{
			seller.setGender(Genders.MALE);
		}else {
			seller.setGender(Genders.FEMALE);
		}
		seller.setRole(Roles.SELLER);
		seller.setPassword(newUser.getPassword());
		seller.setUsername(newUser.getUsername());
		return seller;
	}
	
	@GET
	@Path("/allusers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UsersDTO> getAllUsers(){
		
		ArrayList<UsersDTO> allUsers = new ArrayList<>();
		
		Sellers sellers = new Sellers(ctx.getRealPath("."));
		for(Map.Entry<String, Seller> seller : sellers.getSellers().entrySet())
		{
			Seller user = seller.getValue();
			allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), Roles.SELLER));
		
		}
		Customers customers = new Customers(ctx.getRealPath("."));
		for(Map.Entry<String, Customer> cust : customers.getCustomers().entrySet())
		{
			Customer user = cust.getValue();
			if(user.getCollectedPoints() < 500) {
				
				allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), user.getCollectedPoints(), Roles.CUSTOMER, CustomerTypeNames.BRONZE ));
			}else if(user.getCollectedPoints() <1000){
				allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), user.getCollectedPoints(),Roles.CUSTOMER, CustomerTypeNames.SILVER));
			}else if(user.getCollectedPoints() <1500){
				allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), user.getCollectedPoints(), Roles.CUSTOMER, CustomerTypeNames.GOLD));
			}else if(user.getCollectedPoints() <2000){
				allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), user.getCollectedPoints(), Roles.CUSTOMER, CustomerTypeNames.DIAMOND));
			}
			
		
		}
		Admins admins = new Admins(ctx.getRealPath("."));
		for(Map.Entry<String, Admin> admin : admins.getAdmins().entrySet())
		{
			Admin user = admin.getValue();
			allUsers.add(new UsersDTO(user.getUsername(),user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate(), Roles.ADMIN));
		
		}
		
		return allUsers;
	}
	
}

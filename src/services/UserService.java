package services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import collections.Admins;
import collections.Customers;
import collections.Sellers;

import dto.UserDTO;


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
	
}

package services;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.LoginUserDTO;

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
//		if (tryLoginAdmin(dto.getUsername(), dto.getPassword()))
//			return "admin";
//		else if (tryLoginUser(dto.getUsername(), dto.getPassword()).equalsIgnoreCase("ok"))
//			return "user";
//		else if (tryLoginUser(dto.getUsername(), dto.getPassword()).equalsIgnoreCase("blocked"))
//			return "blocked";
//		else
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());

		return "none";
	}
}
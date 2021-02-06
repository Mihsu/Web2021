package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import collections.Manifestations;
import collections.Tickets;
import dto.TicketDbDTO;
import enums.TicketsStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/tickets")
public class TicketService {

	@Context
	HttpServletRequest req;
	@Context
	ServletContext ctx;
	
	@GET
	@Path("/reserved")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TicketDbDTO> getReservedTickets(){
		
		Tickets tickets = new Tickets(ctx.getRealPath("."));
		
		ArrayList<TicketDbDTO> reserved = new ArrayList<>();
		
		for(Map.Entry<Integer, TicketDbDTO> ticket : tickets.getTickets().entrySet())
		{
			if(ticket.getValue().getStatus() == TicketsStatus.RESERVED) {
				reserved.add(ticket.getValue());
			}
		}
		return reserved;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TicketDbDTO> getAllTickets(){
		
		Tickets tickets = new Tickets(ctx.getRealPath("."));
		
		ArrayList<TicketDbDTO> all = new ArrayList<>();
		
		for(Map.Entry<Integer, TicketDbDTO> ticket : tickets.getTickets().entrySet())
		{
			
			all.add(ticket.getValue());
			
		}
		return all;
	}
	
	@GET
	@Path("/all/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TicketDbDTO> getAllTicketsUser(@PathParam("username") String username) throws Exception{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Tickets tickets = new Tickets(ctx.getRealPath("."));
		
		ArrayList<TicketDbDTO> all = new ArrayList<>();
		Date now = new Date();
		
		for(Map.Entry<Integer, TicketDbDTO> ticket : tickets.getTickets().entrySet())
		{
			if(ticket.getValue().getBuyerUsername().equalsIgnoreCase(username) && ticket.getValue().getStatus().equals(TicketsStatus.RESERVED)) {
				if(sdf.parse(ticket.getValue().getDateAndTime().toString()).before(now) ) {
					ticket.getValue().setCommentable(true);
				}
				all.add(ticket.getValue());	
			}
		}
		return all;
	}
}

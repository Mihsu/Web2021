package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import collections.Manifestations;
import collections.Tickets;
import dto.TicketDbDTO;
import enums.TicketsStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}

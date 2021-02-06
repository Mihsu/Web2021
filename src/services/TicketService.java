package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Manifestation;
import Model.Ticket;
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
import javax.ws.rs.Consumes;
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
	
	@GET
	@Path("/complex/{priceFrom}/{priceTo}/{dateFrom}/{dateTo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<TicketDbDTO> getTicketsComplexSearch(@PathParam("priceFrom")int priceFrom,
			@PathParam("priceTo")int priceTo, @PathParam("dateFrom")String dateFrom, @PathParam("dateTo")String dateTo) throws ParseException  
	{
		
		Tickets tickets = new Tickets(ctx.getRealPath("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<TicketDbDTO> toReturnTickets = new ArrayList<>();
		for(Map.Entry<Integer, TicketDbDTO> ticket : tickets.getTickets().entrySet())
		{
			boolean flag1 = false;
			boolean flag2 = false;
		
	
			
			if(!dateFrom.equalsIgnoreCase("") && !dateTo.equalsIgnoreCase("")) {
				if(sdf2.parse(ticket.getValue().getDateAndTime()).after(sdf.parse(dateFrom))){
					if(sdf2.parse(ticket.getValue().getDateAndTime()).before(sdf.parse(dateTo))){
						flag1 = true;
					}
				}
			}
			if(ticket.getValue().getPrice() >= priceFrom) {
				if(ticket.getValue().getPrice() <= priceTo) {
					flag2 = true;
				}
			
			}
			if(flag1 && flag2 ) {
				toReturnTickets.add(ticket.getValue());
	
			}		
			
			
		}
		
		return toReturnTickets;
	}
}

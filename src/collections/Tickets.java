package collections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TicketDbDTO;

public class Tickets {

private HashMap<Integer, TicketDbDTO> tickets;
	
	public Tickets() {
		this.tickets = new HashMap<Integer, TicketDbDTO>();
	}
	
	public Tickets(String path) {
		this.tickets = this.load(path);
	}

	public Tickets(HashMap<Integer, TicketDbDTO> tickets) {
		super();
		this.tickets = tickets;
	}

	public HashMap<Integer, TicketDbDTO> getTickets() {
		return tickets;
	}

	public void setTickets(HashMap<Integer, TicketDbDTO> tickets) {
		this.tickets = tickets;
	}
	
	public HashMap<Integer,TicketDbDTO> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<Integer,TicketDbDTO>> typeRef = new TypeReference<HashMap<Integer,TicketDbDTO>>() {};
            HashMap<Integer, TicketDbDTO> tickets = objectMapper.readValue(new File(path + "\\databases\\tickets.json"), typeRef);
          
            return tickets;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\tickets.json"), tickets);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

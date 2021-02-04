package collections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Customer;
import Model.User;

public class Customers {

private HashMap<String, Customer> customers;
	
	public Customers() {
		this.customers = new HashMap<String, Customer>();
	}
	
	public Customers(String path) {
		this.customers = this.load(path);
	}

	public Customers(HashMap<String, Customer> customers) {
		super();
		this.customers = customers;
	}

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}
	
	public HashMap<String,Customer> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<String,Customer>> typeRef = new TypeReference<HashMap<String,Customer>>() {};
            HashMap<String, Customer> customers = objectMapper.readValue(new File(path + "\\databases\\customers.json"), typeRef);
          
            return customers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\customers.json"), customers);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

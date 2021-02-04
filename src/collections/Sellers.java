package collections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Admin;
import Model.Seller;


public class Sellers {

private HashMap<String, Seller> sellers;
	
	public Sellers() {
		this.sellers = new HashMap<String, Seller>();
	}
	
	public Sellers(String path) {
		this.sellers = this.load(path);
	}

	public Sellers(HashMap<String, Seller> sellers) {
		super();
		this.sellers = sellers;
	}

	public HashMap<String, Seller> getSellers() {
		return sellers;
	}

	public void setSellers(HashMap<String, Seller> sellers) {
		this.sellers = sellers;
	}
	
	public HashMap<String,Seller> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<String,Seller>> typeRef = new TypeReference<HashMap<String,Seller>>() {};
            HashMap<String, Seller> sellers = objectMapper.readValue(new File(path + "\\databases\\sellers.json"), typeRef);
          
            return sellers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\sellers.json"), sellers);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package collections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Admin;


public class Admins {

private HashMap<String, Admin> admins;
	
	public Admins() {
		this.admins = new HashMap<String, Admin>();
	}
	
	public Admins(String path) {
		this.admins = this.load(path);
	}

	public Admins(HashMap<String, Admin> admins) {
		super();
		this.admins = admins;
	}

	public HashMap<String, Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(HashMap<String, Admin> admins) {
		this.admins = admins;
	}
	
	public HashMap<String,Admin> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<String,Admin>> typeRef = new TypeReference<HashMap<String,Admin>>() {};
            HashMap<String, Admin> admins = objectMapper.readValue(new File(path + "\\databases\\admins.json"), typeRef);
          
            return admins;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\admins.json"), admins);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package collections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Manifestation;

public class Manifestations {
	private HashMap<String, Manifestation> manifestations;
	
	public Manifestations() {
		this.manifestations = new HashMap<String, Manifestation>();
	}
	
	public Manifestations(String path) {
		this.manifestations = this.load(path);
	}

	public Manifestations(HashMap<String, Manifestation> manifestations) {
		super();
		this.manifestations = manifestations;
	}

	public HashMap<String, Manifestation> getManifestatations() {
		return manifestations;
	}

	public void setManifestations(HashMap<String, Manifestation> manifestations) {
		this.manifestations = manifestations;
	}
	
	public HashMap<String,Manifestation> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<String,Manifestation>> typeRef = new TypeReference<HashMap<String,Manifestation>>() {};
            HashMap<String, Manifestation> manifestations = objectMapper.readValue(new File(path + "\\databases\\manifestations.json"), typeRef);
          
            return manifestations;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\manifestations.json"), manifestations);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

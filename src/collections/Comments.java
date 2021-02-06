package collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.CommentDbDTO;

public class Comments {

	private HashMap<Integer, CommentDbDTO> comments;
	
	public Comments() {
		this.comments = new HashMap<Integer, CommentDbDTO>();
	}
	
	public Comments(String path) {
		this.comments = this.load(path);
	}

	public Comments( HashMap<Integer, CommentDbDTO> comments) {
		super();
		this.comments = comments;
	}

	public HashMap<Integer, CommentDbDTO> getComments() {
		return comments;
	}

	public void setComments( HashMap<Integer, CommentDbDTO> comments) {
		this.comments = comments;
	}
	
	public HashMap<Integer, CommentDbDTO> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference<HashMap<Integer, CommentDbDTO>> typeRef = new TypeReference<HashMap<Integer, CommentDbDTO>>() {};
			HashMap<Integer, CommentDbDTO> comments = objectMapper.readValue(new File(path + "\\databases\\comments.json"), typeRef);
          
            return comments;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(path + "\\databases\\comments.json"), comments);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

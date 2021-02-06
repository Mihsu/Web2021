package collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.CommentDbDTO;

public class Comments {

	private ArrayList<CommentDbDTO> comments;
	
	public Comments() {
		this.comments = new ArrayList<CommentDbDTO>();
	}
	
	public Comments(String path) {
		this.comments = this.load(path);
	}

	public Comments( ArrayList<CommentDbDTO> comments) {
		super();
		this.comments = comments;
	}

	public  ArrayList<CommentDbDTO> getComments() {
		return comments;
	}

	public void setComments( ArrayList<CommentDbDTO> comments) {
		this.comments = comments;
	}
	
	public  ArrayList<CommentDbDTO> load(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			TypeReference< ArrayList<CommentDbDTO>> typeRef = new TypeReference< ArrayList<CommentDbDTO>>() {};
			ArrayList<CommentDbDTO> comments = objectMapper.readValue(new File(path + "\\databases\\comments.json"), typeRef);
          
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

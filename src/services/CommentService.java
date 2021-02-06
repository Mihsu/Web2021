package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import collections.Comments;
import collections.Manifestations;
import dto.CommentDbDTO;
import dto.TicketDbDTO;
import enums.TicketsStatus;


@Path("/comment")
public class CommentService {

	@Context
	HttpServletRequest req;
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String postComment(CommentDbDTO comment) {
		
	
		Comments comments = new Comments(ctx.getRealPath("."));
		comment.setApproved(false);
		int id = comments.getComments().size() + 1;
		comment.setId(id);
		comments.getComments().put(id, comment);
		
		comments.save(ctx.getRealPath("."));
		
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));

		double oldRating = manifests.getManifestatations().get(comment.getManifestationName()).getRating();
		double newRating = (oldRating + comment.getRating() )/2;
		manifests.getManifestatations().get(comment.getManifestationName()).setRating(newRating);
		

		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
	@POST
	@Path("/approve/{commentId}")
	public String approveComment(@PathParam("commentId") int commentId) {
		
		
		Comments comments = new Comments(ctx.getRealPath("."));
		comments.getComments().get(commentId).setApproved(true);
		
		comments.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
	@GET
	@Path("/get/{manifestationName}")
	public Collection<CommentDbDTO> getApprovedComments(@PathParam("manifestationName") String manifestationName){
		Comments comments = new Comments(ctx.getRealPath("."));
		
		ArrayList<CommentDbDTO> cmnts = new ArrayList<>();
		for(Map.Entry<Integer, CommentDbDTO> comment : comments.getComments().entrySet())
		{
			if(comment.getValue().getManifestationName().equalsIgnoreCase(manifestationName)) {
				if(comment.getValue().isApproved())
				{
					cmnts.add(comment.getValue());
				}
				
			}
		}
		
		return cmnts;
		
	}
	
	@GET
	@Path("/getall/{manifestationName}")
	public Collection<CommentDbDTO> getAllComments(@PathParam("manifestationName") String manifestationName){
		Comments comments = new Comments(ctx.getRealPath("."));
		
		ArrayList<CommentDbDTO> cmnts = new ArrayList<>();
		for(Map.Entry<Integer, CommentDbDTO> comment : comments.getComments().entrySet())
		{
			if(comment.getValue().getManifestationName().equalsIgnoreCase(manifestationName)) {
				
				cmnts.add(comment.getValue());
				
				
			}
		}
		
		return cmnts;
		

		
	}
}

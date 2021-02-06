package services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import collections.Comments;
import collections.Manifestations;
import dto.CommentDbDTO;


@Path("/comment")
public class CommentService {

	@Context
	HttpServletRequest req;
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String postComment(CommentDbDTO comment) {
		
		System.out.println(comment.toString());
		Comments comments = new Comments(ctx.getRealPath("."));
		comments.getComments().add(comment);
		comments.save(ctx.getRealPath("."));
		
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		System.out.println(manifests.getManifestatations().get(comment.getManifestationName()));
		double oldRating = manifests.getManifestatations().get(comment.getManifestationName()).getRating();
		double newRating = (oldRating + comment.getRating() )/2;
		manifests.getManifestatations().get(comment.getManifestationName()).setRating(newRating);
		
		System.out.println("rating:");
		System.out.println(manifests.getManifestatations().get(comment.getManifestationName()).getRating());
		
		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
}

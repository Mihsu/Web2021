package services;


import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Manifestation;

import collections.Manifestations;
import dto.ManifestationDTO;
import enums.ManifestationsStatus;

@Path("/manifestations")
public class ManifestationService {
	

	@Context
	HttpServletRequest req;
	@Context
	ServletContext ctx;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Manifestation> getAllManifestations() {
		return new Manifestations(ctx.getRealPath(".")).getManifestatations().values();
		
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createManifestation(ManifestationDTO m) {
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		
		Manifestation newMan = new Manifestation(m.getName(), m.getType(), m.getCapacity(), m.getDateAndTime(),m.getRegularPrice(), 
				ManifestationsStatus.INACTIVE, m.getLocation(), "logo.png", 5);
		manifests.getManifestatations().put(newMan.getName(), newMan);
		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateManifestation(ManifestationDTO m) {
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		
		manifests.getManifestatations().get(m.getName()).setCapacity(m.getCapacity());;
		manifests.getManifestatations().get(m.getName()).setDateAndTime(m.getDateAndTime());;
		manifests.getManifestatations().get(m.getName()).setLocation(m.getLocation());;
		manifests.getManifestatations().get(m.getName()).setName(m.getName());;
		manifests.getManifestatations().get(m.getName()).setRegularPrice(m.getRegularPrice());;
		manifests.getManifestatations().get(m.getName()).setType(m.getType());;
		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	

}

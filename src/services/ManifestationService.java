package services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Manifestation;
import Model.ManifestationComplexSearch;
import collections.Manifestations;
import dto.ManifestationDTO;

import enums.ManifestationsStatus;
import enums.TicketsStatus;

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
	
	@GET
	@Path("/allactive")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Manifestation> getAllManifestationsActive() {
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		
		ArrayList<Manifestation> activeMans = new ArrayList<>();
		for(Map.Entry<String, Manifestation> man : manifests.getManifestatations().entrySet())
		{
			if(man.getValue().getStatus() == ManifestationsStatus.ACTIVE) {
				activeMans.add(man.getValue());
			}
		}
		
		return activeMans;
		
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
	
		manifests.getManifestatations().get(m.getName()).setCapacity(m.getCapacity());
		manifests.getManifestatations().get(m.getName()).setDateAndTime(m.getDateAndTime());
		manifests.getManifestatations().get(m.getName()).setLocation(m.getLocation());
		manifests.getManifestatations().get(m.getName()).setName(m.getName());
		manifests.getManifestatations().get(m.getName()).setRegularPrice(m.getRegularPrice());
		manifests.getManifestatations().get(m.getName()).setType(m.getType());
		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
	@POST
	@Path("/approve/{manifestationName}")
	public String approveManifestation(@PathParam("manifestationName")String manifestationName) {
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		
		manifests.getManifestatations().get(manifestationName).setStatus(ManifestationsStatus.ACTIVE);
	
		manifests.save(ctx.getRealPath("."));
		
		return "OK";
	}
	
	@GET
	@Path("/complex/{name}/{city}/{priceFrom}/{priceTo}/{dateFrom}/{dateTo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Manifestation> getManifestationsComplexSearch(@PathParam("name")String name, @PathParam("city")String city, @PathParam("priceFrom")int priceFrom,
			@PathParam("priceTo")int priceTo, @PathParam("dateFrom")String dateFrom, @PathParam("dateTo")String dateTo) throws ParseException  
	{
		System.out.println(name + city + priceFrom + priceTo + dateFrom + dateTo);
		Manifestations manifests = new Manifestations(ctx.getRealPath("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ArrayList<Manifestation> toReturnManifestations = new ArrayList<>();
		for(Map.Entry<String, Manifestation> manifestation : manifests.getManifestatations().entrySet())
		{
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			boolean flag4 = false;
	
			//is name empty
			if(manifestation.getKey().contains(name)){
				flag1 = true;
			}
			if(manifestation.getValue().getLocation().getAddress().getCity().contains(city)) {
				flag2 = true;
			}
			if(!dateFrom.equalsIgnoreCase("") && !dateTo.equalsIgnoreCase("")) {
				if(sdf.parse(manifestation.getValue().getDateAndTime()).after(sdf.parse(dateFrom))){
					if(sdf.parse(manifestation.getValue().getDateAndTime()).before(sdf.parse(dateTo))){
						flag3 = true;
					}
				}
			}
			if(manifestation.getValue().getRegularPrice() >= priceFrom) {
				if(manifestation.getValue().getRegularPrice() <= priceTo) {
					flag4 = true;
				}
			
			}
			if(flag1 && flag2 && flag3 && flag4) {
				toReturnManifestations.add(manifestation.getValue());
	
			}		
			
			
		}
		
		return toReturnManifestations;
	}
}

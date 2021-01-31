package Model;

import java.util.List;

public class Seller extends User {

	List<Manifestation> manifestations;

	
	public Seller() {
		super();

	}
	public Seller(List<Manifestation> manifestations) {
		super();
		this.manifestations = manifestations;
	}

	public List<Manifestation> getManifestations() {
		return manifestations;
	}

	public void setManifestations(List<Manifestation> manifestations) {
		this.manifestations = manifestations;
	}
	
	
}

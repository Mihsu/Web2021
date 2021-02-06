package Model;

public class ManifestationComplexSearch {
	
	private String name;
	private String city;
	private int priceFrom;
	private int priceTo;
	private String dateFrom;
	private String dateTo;
	
	public ManifestationComplexSearch() {
		super();
		this.name = "";
		this.city = "";
		this.priceFrom = 0;
		this.priceTo = 1111111;
		this.dateFrom = "1900-01-01 10:10";
		this.dateTo = "2050-01-01 10:10";
	}
	public ManifestationComplexSearch( String dateFrom,
			String dateTo) {
		super();
		this.name = "";
		this.city = "";
		this.priceFrom = 0;
		this.priceTo = 1111111;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public ManifestationComplexSearch( int priceFrom, int priceTo) {
		super();
		this.name = "";
		this.city = "";
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.dateFrom = "1900-01-01 10:10";
		this.dateTo = "2050-01-01 10:10";
	}
	public ManifestationComplexSearch(String name, String city, int priceFrom, int priceTo, String dateFrom,
			String dateTo) {
		super();
		this.name = name;
		this.city = city;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}


	public ManifestationComplexSearch(String name, int priceFrom, int priceTo, String dateFrom,
			String dateTo) {
		super();
		this.name = name;
		this.city = "";
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}
	public int getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	@Override
	public String toString() {
		return "ManifestationComplexSearch [name=" + name + ", city=" + city + ", priceFrom=" + priceFrom + ", priceTo="
				+ priceTo + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
	}
	
	

}

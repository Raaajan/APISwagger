package utility;

public enum Resources {

	postProductURI("/products"),
	getProductURI("/products");		
	
	private String resource;
	
	Resources(String resource){
		this.resource = resource;
	}

	public String getResource() {
		
		return resource;
	}
}

package utility;

public enum Resources {

	postProductURI("/products"),
	getProductURI("/products"),	
	deleteProductURI("/products"),
	patchProductURI("/products");
	
	private String resource;
	
	Resources(String resource){
		this.resource = resource;
	}

	public String getResource() {
		
		return resource;
	}
}

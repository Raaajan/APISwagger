package postpayload;

import pojo.ProductPost;

public class ProductPostPayload {

	ProductPost pp = new ProductPost();
	
	public ProductPost setProductPostPayload() {
		
		pp.setName("string");
		pp.setType("string");
		pp.setPrice(0);
		pp.setShipping(0);
		pp.setUpc("upc");
		pp.setDescription("description");
		pp.setManufacturer("manufacturer");
		pp.setModel("model");
		pp.setUrl("url");
		pp.setImage("image");
		
		return pp;
	}
	
}

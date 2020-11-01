package postpayload;

import pojo_request.ProductPostReq;

public class ProductPostPayload {

	ProductPostReq pp = new ProductPostReq();
	
	public ProductPostReq setProductPostPayload(String name,String type,int price,int shipping,String upc,String description,String manufacturer,String model,String url,String image) {
		
		pp.setName(name);
		pp.setType(type);
		pp.setPrice(price);
		pp.setShipping(shipping);
		pp.setUpc(upc);
		pp.setDescription(description);
		pp.setManufacturer(manufacturer);
		pp.setModel(model);
		pp.setUrl(url);
		pp.setImage(image);
		
		return pp;
	}
	
}

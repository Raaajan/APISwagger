package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo_request.ProductPostReq;
import pojo_response.ProductGet;
import pojo_response.ProductPost;
import postpayload.ProductPostPayload;
import utility.ExcelReader;
import utility.Helper;
import utility.Resources;

public class ProductAPIStepDefinition extends Helper{
	
	ProductPostReq propostPay;
	Response res;
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	static int count=0;

@Given("user has URI")
public void user_has_URI() throws IOException {
	
		reqspec = requestSpecification();
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build(); 
	}

@And("user has payload {string},{string},{int},{int},{string},{string},{string},{string},{string},{string}")
public void user_has_payload(String name,String type,int price,int shipping,String upc,String description,String manufacturer,String model,String url,String image) {
 
		ProductPostPayload PPpayload = new ProductPostPayload();
		 propostPay = PPpayload.setProductPostPayload(name,type,price,shipping,upc,description,manufacturer,model,url,image);
}

@When("user trigger {string} with {string}")
public void user_trigger(String requesttype,String resources) {
		
		Resources resource = Resources.valueOf(resources);
		String resourceval = resource.getResource();
		
		if(requesttype.equalsIgnoreCase("POST")) {
		res = given().log().all().spec(reqspec).body(propostPay).when().post(resourceval)
			.then().log().all().spec(resspec).extract().response();
		}
		else if(requesttype.equalsIgnoreCase("GET")) {
		res	= given().log().all().spec(reqspec).when().get(resourceval)
			.then().log().all().spec(resspec).extract().response();
		}
	}

@When("user trigger {string} with {string} for {string}")
public void user_trigger_with_for(String requesttype, String resources, String id) {
	
	Resources resource = Resources.valueOf(resources);
	String resourceval = resource.getResource();
	
	if(requesttype.equalsIgnoreCase("DELETE")) {
		res	= given().log().all().spec(reqspec).pathParam("id", id).when().delete(resourceval+"/{id}")
				.then().log().all().spec(resspec).extract().response();
	}
	else if(requesttype.equalsIgnoreCase("PATCH")) {
		res	= given().log().all().spec(reqspec).body(propostPay).pathParam("id", id).when().patch(resourceval+"/{id}")
				.then().log().all().spec(resspec).extract().response();
	}
}

@Then("verify {int} code and response body for {string}")
public void verify_code_and_response_body_for(int Estatuscode, String requesttype) throws IOException {
	
	ExcelReader er = new ExcelReader();
	
	int Astatuscode = res.getStatusCode();
	System.out.println("Response Status Code : "+Astatuscode);
	assertEquals(Estatuscode, Astatuscode);
	if(requesttype.equalsIgnoreCase("POST")||requesttype.equalsIgnoreCase("DELETE")||requesttype.equalsIgnoreCase("PATCH")) {
		System.out.println("**********POST/PATCH RESPONSE************");
		List<HashMap<String, String>> postprorespayload = er.getListMap("PostProductResponsePayload");
		ProductPost postRes = res.as(ProductPost.class);
		System.out.println("count "+count);
		String Atype = postRes.getType();
		String Aname = postRes.getName();
		int Aprice = postRes.getPrice();
		
		System.out.println("Atype : "+Atype);
		System.out.println("Aname : "+Aname);
		System.out.println("Aprice : "+Aprice);
		
			String Etype =postprorespayload.get(count).get("type");
			String Ename = postprorespayload.get(count).get("name");
			String price =postprorespayload.get(count).get("price");
			int Eprice = Integer.parseInt(price);
			System.out.println("Etype : "+Etype);
			System.out.println("Ename : "+Ename);
			System.out.println("Eprice : "+Eprice);
			
			assertEquals(Etype, Atype);
			assertEquals(Ename, Aname);
			assertEquals(Aprice, Eprice);
		
		count++;
	
	}
	
	if(requesttype.equalsIgnoreCase("GET")) {
	System.out.println("*************GET RESPONSE**************");
	List<HashMap<String, String>> getprorespayload = er.getListMap("GetProductResponsePayload");
	ProductGet getRes = res.as(ProductGet.class);
	
	 System.out.println(getRes.getTotal());
	 System.out.println(getRes.getLimit());
	 System.out.println(getRes.getData().get(0).getId());
	 System.out.println(getRes.getData().get(0).getName());
	 System.out.println(getRes.getData().get(0).getCategories().get(0).getId());
	 System.out.println(getRes.getData().get(5).getId());
	 System.out.println(getRes.getData().get(5).getName());
	 System.out.println(getRes.getData().get(5).getCategories().get(0).getName());
	 
	 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
	 System.out.println(getprorespayload.get(0).get("id"));
	 System.out.println(getprorespayload.get(0).get("name"));
	 System.out.println(getprorespayload.get(0).get("categories/0/id"));
	 System.out.println(getprorespayload.get(5).get("id"));
	 System.out.println(getprorespayload.get(5).get("name"));
	 System.out.println(getprorespayload.get(5).get("categories/0/name"));
	 
	 assertEquals(getprorespayload.get(0).get("id"),getRes.getData().get(0).getId());
	 assertEquals(getprorespayload.get(0).get("name"),getRes.getData().get(0).getName());
	 assertEquals(getprorespayload.get(0).get("categories/0/id"),getRes.getData().get(0).getCategories().get(0).getId());
	 assertEquals(getprorespayload.get(5).get("id"),getRes.getData().get(5).getId());
	 assertEquals(getprorespayload.get(5).get("name"),getRes.getData().get(5).getName());
	 assertEquals(getprorespayload.get(5).get("categories/0/name"),getRes.getData().get(5).getCategories().get(0).getName());
	
	}
	    
	}
	
	
}

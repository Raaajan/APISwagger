package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
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
import pojo_request.ProductPostReq;
import pojo_response.ProductGet;
import pojo_response.ProductPost;
import postpayload.ProductPostPayload;
import utility.Helper;
import utility.Resources;

public class ProductAPIStepDefinition extends Helper{
	
	ProductPostReq propostPay;
	Response res;
	RequestSpecification reqspec;
	ResponseSpecification resspec;

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
/*
@Then("verify {int} code and response body")
public void verify_code_and_response_body(int Astatuscode) {*/

@Then("verify {int} code and response body for {string}")
public void verify_code_and_response_body_for(int Astatuscode, String requesttype) {
		
	int Estatuscode = res.getStatusCode();
	assertEquals(Astatuscode, Estatuscode);
	
	if(requesttype.equalsIgnoreCase("POST")) {
		System.out.println("**********POST RESPONSE************");
		ProductPost postRes = res.as(ProductPost.class);
		
		System.out.println(postRes.getId());
		System.out.println(postRes.getName());
		System.out.println(postRes.getPrice());
	}
	
	if(requesttype.equalsIgnoreCase("GET")) {
	System.out.println("*************GET RESPONSE**************");	
	ProductGet getRes = res.as(ProductGet.class);
	
	 System.out.println(getRes.getTotal());
	 System.out.println(getRes.getLimit());
	 System.out.println(getRes.getData().get(0).getId());
	 System.out.println(getRes.getData().get(0).getName());
	 System.out.println(getRes.getData().get(0).getCategories().get(0).getId());
	 System.out.println(getRes.getData().get(5).getId());
	 System.out.println(getRes.getData().get(5).getName());
	 System.out.println(getRes.getData().get(5).getCategories().get(0).getId());
	}
	    
	}
	
	
}

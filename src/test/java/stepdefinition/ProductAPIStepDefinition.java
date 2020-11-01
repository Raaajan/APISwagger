package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ProductPost;
import postpayload.ProductPostPayload;
import utility.Helper;
import utility.Resources;

public class ProductAPIStepDefinition extends Helper{
	
	ProductPost propostPay;
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

@Then("verify {int} code and response body")
public void verify_code_and_response_body(int Astatuscode) {
		
	int Estatuscode = res.getStatusCode();
	assertEquals(Astatuscode, Estatuscode);
	    
	}
	
	
}

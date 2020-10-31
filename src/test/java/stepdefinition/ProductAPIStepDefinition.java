package stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ProductPost;
import postpayload.ProductPostPayload;
import utility.Helper;

public class ProductAPIStepDefinition extends Helper{
	
	ProductPost propostPay;
	String res;
	RequestSpecification reqspec;
	ResponseSpecification resspec;

@Given("user has URI")
public void user_has_URI() throws IOException {
	   
		ProductPostPayload PPpayload = new ProductPostPayload();
		
		propostPay = PPpayload.setProductPostPayload();
		
		Properties conf = loadPropertiesFile(System.getProperty("user.dir")+"\\src\\test\\resources\\Config.properties");
		
		RestAssured.baseURI =  conf.getProperty("baseuri");
		//String baseuri =conf.getProperty("baseuri");
		
		reqspec = requestSpecification();
	//	reqspec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build(); 
	}

@When("user trigger {string}")
public void user_trigger(String string) {
		
		res = given().spec(reqspec).log().all().body(propostPay).log().all().when().post("/products")
			.then().log().all().extract().asString();
		
		given().spec(reqspec).log().all().body(propostPay).log().all().when().post("/products")
		.then().log().all().statusCode(201);
	   
	}

@Then("user get response code and response body")
public void user_get_response_code_and_response_body() {
		
		JsonPath json = new JsonPath(res);
		
		
		String id = json.getString("id");
		String name = json.getString("name");
		String model = json.getString("model");
		
		System.out.println("id : "+id+" name : "+name +" model : "+model);
	    
	}
	
	
}

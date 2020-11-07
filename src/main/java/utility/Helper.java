package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Helper{
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
	
		PrintStream logger = new PrintStream(new FileOutputStream("logger.txt"));
		req = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(getValuefromPropertiesFile("baseuri"))
		.addFilter(RequestLoggingFilter.logRequestTo(logger))
		.addFilter(ResponseLoggingFilter.logResponseTo(logger))
		.build();
		
		return req;
		}
		return req;
	}
	

	public String getValuefromPropertiesFile(String key) throws IOException {
		FileInputStream fis = null;
		try {
			 File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Config.properties");
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		prop.load(fis);
		
		return prop.getProperty(key);
	}
	
	public String getJsonPathValue(Response resp, String Jkey) {
		
		JsonPath json = new JsonPath(resp.asString());
		return json.getString(Jkey);
		
	}
	
}

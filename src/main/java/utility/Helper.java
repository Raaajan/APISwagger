package utility;

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
import io.restassured.specification.RequestSpecification;



public class Helper{
	RequestSpecification req;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		
	
		PrintStream logger = new PrintStream(new FileOutputStream("logger.txt"));
		req = new RequestSpecBuilder().setContentType(ContentType.JSON)
		.addFilter(RequestLoggingFilter.logRequestTo(logger))
		.addFilter(ResponseLoggingFilter.logResponseTo(logger))
		.build();
		
		return req;
	}
	

	public Properties loadPropertiesFile(String file) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		prop.load(fis);
		
		return prop;
	}
	
}

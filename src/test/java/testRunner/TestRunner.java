package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features="D:\\Automation\\Eclipse\\apiFramework\\src\\test\\resources\\products.feature",
		glue= {"stepdefinition"}
		
		
		)
public class TestRunner {
{
	
}
}

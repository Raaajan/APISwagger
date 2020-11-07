package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features="D:\\Automation\\Eclipse\\apiFramework\\src\\test\\resources\\products.feature",
		glue= {"stepdefinition"},
		plugin = {"json:target\\jsonReports\\executionReport.json"},
		tags= "@POST" 
		)
public class TestRunner  {
	
}

// if using TestNG then use below code. modify dependencies accordingly.

/*@CucumberOptions(features = "D:\\Automation\\Eclipse\\apiFramework\\src\\test\\resources\\products.feature", 
 * glue = "stepdefinition",strict = true)
public class TestRunner extends AbstractTestNGCucumberTests  {
	
}*/

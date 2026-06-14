package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/incident-table-refactored.feature"},
		          glue = {"step.defs"},
		          plugin = {
		        		  "pretty",
		        		  "html:cucumber-reports/result.html",
		        		  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		          }
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
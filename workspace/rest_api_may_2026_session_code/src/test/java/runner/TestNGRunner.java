package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/incident-table.feature"},
		          glue = {"step.defs"}
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
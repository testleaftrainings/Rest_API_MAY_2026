package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features/"},
        glue = {"step.defs"},
        dryRun = true
      )
public class SyncTest extends AbstractTestNGCucumberTests {

}
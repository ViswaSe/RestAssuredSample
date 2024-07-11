package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/features/ChangeRequestModule.feature"},
		glue= {"steps"},
		monochrome = true)
public class ChangeRequestRunner extends AbstractTestNGCucumberTests {

}

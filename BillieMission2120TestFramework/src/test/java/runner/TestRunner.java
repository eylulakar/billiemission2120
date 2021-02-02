package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        glue ={"stepdefinitions"},
        features = {"src/test/resources/features"}

)
public class TestRunner extends AbstractTestNGCucumberTests {


}

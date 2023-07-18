import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/user/Documents/Java_Automation/sales-app/src/test/resources/features/oc/oc_max-1.feature"},
        plugin = {"json:/Users/user/Documents/Java_Automation/sales-app/target/cucumber-reports/1.json"},
        monochrome = true,
        tags = {"@oc_max", "not @ignore"},
        glue = {"er.automation", "exp.layer"})
public class Parallel01IT {
}

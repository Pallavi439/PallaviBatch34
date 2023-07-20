import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/user/Documents/Java_Automation/sales-app/src/test/resources/features/oc/oc_min_order_is.feature"},
        plugin = {"json:/Users/user/Documents/Java_Automation/sales-app/target/cucumber-reports/1.json"},
        monochrome = true,
        tags = {"@oc_min_is", "not @ignore"},
        glue = {"er.automation", "exp.layer"})
public class Parallel01IT {
}

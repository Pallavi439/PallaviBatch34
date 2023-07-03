import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/user/Documents/git/sales-app/src/test/resources/features/e2e/cash-order-placement.feature"},
        plugin = {"json:/Users/user/Documents/git/sales-app/target/cucumber-reports/1.json"},
        monochrome = true,
        tags = {"@cash-order", "not @ignore"},
        glue = {"er.automation", "exp.layer"})
public class Parallel01IT {
}

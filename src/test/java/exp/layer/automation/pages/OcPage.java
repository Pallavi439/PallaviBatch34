package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static org.testng.Assert.assertTrue;

public class OcPage extends Step {

    static Logger log = LogManager.getLogger(OcPage.class);


    public static FlutterElement OC_STRIP = getFlutterActions().getFlutterFinder().byValueKey("oc_order_constraint_strip");

    public static void clickOnOcStrip() throws Exception {
        getFlutterActions().click(OC_STRIP);
    }

    public static void clickOnOcTitle(String index) {
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("oc_condition_strip" + index));
    }

    public static void verifyAbsenceOfOcStrip() {
        getFlutterActions().waitForAbsence(OC_STRIP);
    }

    public static void verifyFinalPriceOnCartPage(String grand_total, String oc_name) {
        grand_total = AutomationUtils.getTestData(grand_total);
        oc_name = AutomationUtils.getTestData(oc_name);
        System.out.println("Grand total ===========================" + grand_total);
        if(oc_name.equalsIgnoreCase("less"))
        {
            assertTrue(Integer.parseInt(grand_total) < 200, "grand total value should be less than 200 value.");
        }

        if(oc_name.equalsIgnoreCase("min"))
        {
            assertTrue(Integer.parseInt(grand_total) >= 200, "grand total value should be greater than or equal to 200 value.");
        }

        if(oc_name.equalsIgnoreCase("lessmax"))
        {
            assertTrue(Integer.parseInt(grand_total) < 500, "grand total value should be greater than or equal to 500 value.");
        }

        if(oc_name.equalsIgnoreCase("max"))
        {
            assertTrue(Integer.parseInt(grand_total) > 500, "grand total value should be greater than 500 value.");
        }
    }
}

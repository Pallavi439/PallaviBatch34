package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertTrue;

public class OcPage extends Step {

    static Logger log = LogManager.getLogger(OcPage.class);

    public static String OC_STRIP = "oc_order_constraint_strip";

    public static void clickOnOcStrip() {
        getMobileActions().flutterClick(OC_STRIP);
    }

    public static void clickOnOcTitle(String index) {
        getMobileActions().flutterClick("oc_condition_strip" + index);
    }

    public static void verifyAbsenceOfOcStrip() {
        getMobileActions().flutterWaitForInVisibility(OC_STRIP);
    }

    public static void verifyFinalPriceOnCartPage(String grand_total, String oc_name) {
        grand_total = AutomationUtils.getTestData(grand_total);
        oc_name = AutomationUtils.getTestData(oc_name);
        log.info("Grand total ===========================" + grand_total);
        if (oc_name.equalsIgnoreCase("less")) {
            assertTrue(Integer.parseInt(grand_total) < 200, "grand total value should be less than 200 value.");
        }
        if (oc_name.equalsIgnoreCase("min")) {
            assertTrue(Integer.parseInt(grand_total) >= 200, "grand total value should be greater than or equal to 200 value.");
        }
        if (oc_name.equalsIgnoreCase("lessmax")) {
            assertTrue(Integer.parseInt(grand_total) < 500, "grand total value should be greater than or equal to 500 value.");
        }
        if (oc_name.equalsIgnoreCase("max")) {
            assertTrue(Integer.parseInt(grand_total) > 500, "grand total value should be greater than 500 value.");
        }
    }
}

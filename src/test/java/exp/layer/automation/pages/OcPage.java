package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class OcPage extends Step {

    static Logger log = LogManager.getLogger(OcPage.class);


    public static FlutterElement OC_STRIP = getFlutterActions().getFlutterFinder().byValueKey("oc_order_constraint_strip");

    public static void clickOnOcStrip() throws Exception {
        CartPage.placeOrderButton();
//        if(Integer.parseInt(grand_total) > 200)
//        {
//            System.out.println("Oc for min order 200 satisfied");
//        }
//        else if(Integer.parseInt(grand_total) < 500)
//        {
//            System.out.println("Oc for max order 500 satisfied");
//        }
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
        if(oc_name.equalsIgnoreCase("min"))
        {

        }
        if (Integer.parseInt(grand_total) > 200) {
            System.out.println("Oc for min order 200 satisfied");
        } else if (Integer.parseInt(grand_total) < 500) {
            System.out.println("Oc for max order 500 satisfied");
        }
    }
}

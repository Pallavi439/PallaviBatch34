package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OcPage extends Step {

    static Logger log = LogManager.getLogger(OcPage.class);


    public static FlutterElement OC_STRIP = getFlutterActions().getFlutterFinder().byValueKey("oc_order_constraint_strip");

    public static void clickOnOcStrip() {
        getFlutterActions().click(OC_STRIP);
    }

    public static void clickOnOcTitle(String index) {
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("oc_condition_strip" + index));
    }

    public static void verifyAbsenceOfOcStrip() {
        getFlutterActions().waitForAbsence(OC_STRIP);
    }
}

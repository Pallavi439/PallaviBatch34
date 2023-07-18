package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class DashboardPage extends Step {

    static Logger log = LogManager.getLogger(DashboardPage.class);
    public static FlutterElement HAMBURGER_MENU_BUTTON=getFlutterActions().getFlutterFinder().byValueKey("home_hamburger_menu");
    public static By BEAT_PLAN_BUTTON = By.xpath("//*[@content-desc='Start Beat' or @content-desc='Continue' or @content-desc='Start Day']");

    public static void clickOnBeatButton() throws Exception {
        getFlutterActions().waitForVisibility(HAMBURGER_MENU_BUTTON);
        getMobileActions().click(BEAT_PLAN_BUTTON);
    }
}

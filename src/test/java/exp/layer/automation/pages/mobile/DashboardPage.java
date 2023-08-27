package exp.layer.automation.pages.mobile;

import er.automation.engine.setup.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class DashboardPage extends Step {

    static Logger log = LogManager.getLogger(DashboardPage.class);

    public static String HAMBURGER_MENU_BUTTON = "home_hamburger_menu";
    public static String END_BEAT_BUTTON="home_end_beat_button";

    public static By BEAT_PLAN_BUTTON = By.xpath("//*[@content-desc='Start Beat' or @content-desc='Continue' or @content-desc='Start Day']");

    public static void clickOnBeatButton() {
        getMobileActions().flutterWaitForVisibility(HAMBURGER_MENU_BUTTON);
        getMobileActions().waitForSeconds(5);
        getMobileActions().waitForVisibilityOfElementLocated(BEAT_PLAN_BUTTON);
        getMobileActions().click(BEAT_PLAN_BUTTON);
        getMobileActions().flutterWaitForVisibility(END_BEAT_BUTTON);
    }
}

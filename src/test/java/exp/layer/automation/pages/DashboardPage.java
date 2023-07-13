package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.github.ashwith.flutter.FlutterElement;
import org.openqa.selenium.By;

public class DashboardPage extends Step {
    //flutter
    public static FlutterElement HOME_HAMBURGER_MENU = getFlutterActions().getFlutterFinder().byValueKey("home_hamburger_menu");
    public static FlutterElement TODAYS_BEAT_LINK = getFlutterActions().getFlutterFinder().byValueKey("home_drawer_menu_Today's Beat");

    //appium
    public static By BEAT_PLAN_BUTTON = By.xpath("//*[@content-desc='Start Beat' or @content-desc='Continue' or @content-desc='Start Day']");

    public static void clickOnBeatButton() throws Exception {
        getFlutterActions().click(HOME_HAMBURGER_MENU);
        getMobileActions().click(new AppiumBy.ByAccessibilityId("Today's Beat"));
        //getMobileActions().click(BEAT_PLAN_BUTTON);
    }
}

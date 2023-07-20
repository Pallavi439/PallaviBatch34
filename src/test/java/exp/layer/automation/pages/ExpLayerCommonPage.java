package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ExpLayerCommonPage extends Step {

    public static FlutterElement HAMBURGER_MENU_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("home_hamburger_menu");
    public static FlutterElement LOGOUT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("logout_widget");

    static Logger log = LogManager.getLogger(ExpLayerCommonPage.class);

    public static void login() {

    }

    public static void selectLanguage() {

    }

    public static void permissionToLocation() {

    }

    public static void saveCartItemQuantity(String item_name, String current_quantity) {
        getUiActions().saveTestData("cart_" + item_name, current_quantity);
    }

    public static String getCartItemQuantity(String item_name) {
        String temp_quantity;
        try {
            temp_quantity = getUiActions().getTestData("cart_" + item_name);
            return temp_quantity;
        } catch (Exception e) {
            return "0";
        }
    }

    public static void verifyOrderTimer() throws Exception {
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("(//*[contains(@content-desc,'Categories')]/preceding-sibling::*)[1]"));
        getMobileActions().waitForSeconds(2);
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("beat_plan_search_back"));
        getUiActions().waitForSeconds(2);
    }

    public static void log_out() {
    }

    public static void captureImage() {
        getUiActions().waitForSeconds(2);
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getUiActions().waitForSeconds(2);
    }

    public static void logOut() {
        getFlutterActions().waitForVisibility(HAMBURGER_MENU_BUTTON);
        getFlutterActions().click(HAMBURGER_MENU_BUTTON);
        getUiActions().waitForSeconds(3);
        getMobileActions().click(AppiumBy.accessibilityId("Logout"));
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[@content-desc='Yes']"));
        getUiActions().waitForSeconds(2);
        getFlutterActions().waitForVisibility(LoginPage.DEV_LOGIN_ER_LOGO);
    }

    public void placeNormalOrder() {
    }
}
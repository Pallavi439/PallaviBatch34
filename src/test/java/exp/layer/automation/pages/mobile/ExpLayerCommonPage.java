package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ExpLayerCommonPage extends Step {

    static Logger log = LogManager.getLogger(ExpLayerCommonPage.class);

    public static String HAMBURGER_MENU_BUTTON = "home_hamburger_menu";
    public static String LOGOUT_BUTTON = "logout_widget";

    public static void saveCartItemQuantity(String item_name, String current_quantity) {
        getUiActions().saveTestData("cart_" + item_name, current_quantity);
    }

    public static String getCartItemQuantity(String item_name) {
        String temp_quantity;
        try {
            temp_quantity = AutomationUtils.getTestData("cart_" + item_name);
            return temp_quantity;
        } catch (Exception e) {
            return "0";
        }
    }

    public static void verifyOrderTimer() {
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
        getMobileActions().flutterClick("beat_plan_search_back");
        getUiActions().waitForSeconds(2);
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
        getMobileActions().flutterWaitForVisibility(HAMBURGER_MENU_BUTTON);
        getMobileActions().flutterClick(HAMBURGER_MENU_BUTTON);
        getUiActions().waitForSeconds(3);
        getMobileActions().click(AppiumBy.accessibilityId("Logout"));
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[@content-desc='Yes']"));
        getUiActions().waitForSeconds(2);
        getMobileActions().flutterWaitForVisibility(LoginPage.DEV_LOGIN_ER_LOGO);
    }
}
package exp.layer.automation.pages.mobile;

import er.automation.engine.actions.MobileActions;
import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class ExpLayerCommonPage extends Step {

    static Logger log = LogManager.getLogger(ExpLayerCommonPage.class);

    public static String HAMBURGER_MENU_BUTTON = "home_hamburger_menu";
    public static String HOME_NOTIFICATION_ICON="home_notification_icon";
    public static String NOTIFICATIONS="notification_card_%s";
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
        getMobileActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("(//*[contains(@content-desc,'Categories')]/preceding-sibling::*)[1]"));
        getMobileActions().waitForSeconds(2);
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().flutterClick("beat_plan_search_back");
        getMobileActions().waitForSeconds(2);
    }

    public static void captureImage() {
        getMobileActions().waitForSeconds(2);
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getMobileActions().waitForSeconds(2);
    }

    public static void logOut() {
        getMobileActions().flutterWaitForVisibility(HAMBURGER_MENU_BUTTON);
        getMobileActions().flutterClick(HAMBURGER_MENU_BUTTON);
        getMobileActions().waitForSeconds(3);
        getMobileActions().click(AppiumBy.accessibilityId("Logout"));
        getMobileActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[@content-desc='Yes']"));
        getMobileActions().waitForSeconds(2);
        getMobileActions().flutterWaitForVisibility(LoginPage.DEV_LOGIN_ER_LOGO);
    }

    public static void backButton(){
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.BACK);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
    }
    public static void backToHomePage(){
        for(int i=0;i<10;i++){
            try {
                Long t= 5000L;
                getMobileActions().flutterWaitForVisibility(HAMBURGER_MENU_BUTTON,t);
                i=10;
            }
            catch (Exception e){
                if (i==9){
                    Assert.fail("unable to reach home page from here");
                }
                backButton();
            }
        }
    }
    public static void verifyOrderPlacementNotification(){
        getMobileActions().flutterWaitForVisibility(HOME_NOTIFICATION_ICON);
        getMobileActions().flutterClick(HOME_NOTIFICATION_ICON);
        getMobileActions().flutterWaitForVisibility(String.format(NOTIFICATIONS,0));
        String gt=AutomationUtils.getTestData("${NOTIFICATION_GRAND_TOTAL_AMOUNT}");
        gt=gt.substring(2);
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath(String.format("(//*[contains(@content-desc,'%s')])[1]",gt)));

    }

}
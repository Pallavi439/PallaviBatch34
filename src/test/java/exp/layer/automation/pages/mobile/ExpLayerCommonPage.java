package exp.layer.automation.pages.mobile;

import er.automation.engine.actions.MobileActions;
import er.automation.engine.context.MobileTestContext;
import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.an.E;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

public class ExpLayerCommonPage extends Step {

    static Logger log = LogManager.getLogger(ExpLayerCommonPage.class);

    public static String HAMBURGER_MENU_BUTTON = "home_hamburger_menu";
    public static String HAMBURGER_PROFILE_BUTTON="home_drawer_profile";
    public static String EDIT_PROFILE_BUTTON="profile_card_edit_profile";
    public static String UPLOAD_PROFILE_IMAGE_BUTTON="profile_image_widget_camera";
    public static String PROFILE_CHANGE_PHOTO_BUTTON="profile_add_photo_change_photo";
    public static String PROFILE_ADD_PHOTO_BUTTON="profile_add_photo_save";
    public static String HOME_NOTIFICATION_ICON="home_notification_icon";
    public static String BANNER_CLOSE_BUTTON="startup_banner_close_icon";
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

    public static void backButton() {
        getMobileActions().verifyContextAndSwitchToNativeContext();
//        KeyEvent event = new KeyEvent(AndroidKey.BACK);
//        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        ((AndroidDriver) getMobileActions().appiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        ((AndroidDriver) getMobileActions().appiumDriver).navigate().back();
    }
    public static void backToHomePage(){
        getMobileActions().flutterClick("beat_plan_search_back");
        getMobileActions().waitForSeconds(5);
        getMobileActions().flutterClick("app_back_button_icon");
        getMobileActions().waitForSeconds(5);
        getMobileActions().click(By.xpath("(//*[contains(@content-desc,'Today')]/preceding-sibling::*)[1]"));
        getMobileActions().waitForSeconds(15);
        try {

        }
        catch (Exception e){
            getMobileActions().waitForSeconds(4);
            getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[contains(@content-desc,'Today')]/preceding-sibling::*"),5000l);
            getMobileActions().click(By.xpath("//*[contains(@content-desc,'Today')]/preceding-sibling::*"));
        }
        getMobileActions().flutterWaitForVisibility(HAMBURGER_MENU_BUTTON);
    }
    public static void verifyOrderPlacementNotification(){
        getMobileActions().flutterWaitForVisibility(HOME_NOTIFICATION_ICON);
        getMobileActions().flutterClick(HOME_NOTIFICATION_ICON);
        getMobileActions().flutterWaitForVisibility(String.format(NOTIFICATIONS,0));
        String gt=AutomationUtils.getTestData("${NOTIFICATION_GRAND_TOTAL_AMOUNT}");
        gt=gt.substring(2);
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath(String.format("(//*[contains(@content-desc,'%s')])[1]",gt)));
    }

    public static void addImageToDownloadsFolder() throws IOException {
        getMobileActions().switchToNativeContext();
        AndroidDriver driver = (AndroidDriver) MobileTestContext.getAppiumContext().getAppiumDriver();

        driver.pushFile("/sdcard/Download/some.png", new File("src/test/resources/test-data/UploadImages/some.png"));
    }
    public static void editsSalesPersonProfile(){
        getMobileActions().flutterClick(HAMBURGER_MENU_BUTTON);
//        getMobileActions().flutterClick(HAMBURGER_PROFILE_BUTTON);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Profile')]"));
        getMobileActions().flutterClick(EDIT_PROFILE_BUTTON);
        getMobileActions().flutterClick(UPLOAD_PROFILE_IMAGE_BUTTON);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Yes')]"));
        getMobileActions().flutterClick(PROFILE_CHANGE_PHOTO_BUTTON);
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[@text='Recent']"));
        getMobileActions().click(By.xpath("(//*[contains(@content-desc,'some.png')])[1]"));
        getMobileActions().click(By.xpath("//*[@content-desc='Crop']"));
        getMobileActions().flutterClick(PROFILE_ADD_PHOTO_BUTTON);
        getMobileActions().waitForSeconds(3);
        getMobileActions().flutterWaitForVisibility(UPLOAD_PROFILE_IMAGE_BUTTON);
    }

    public static void closeBannerIfVisible(){
        try {
            getMobileActions().flutterWaitForVisibility(BANNER_CLOSE_BUTTON);
            getMobileActions().flutterClick(BANNER_CLOSE_BUTTON);
        }
        catch (Exception ignored){}
    }
}
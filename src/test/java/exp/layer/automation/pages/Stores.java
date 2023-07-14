package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Stores extends Step {

    static Logger log = LogManager.getLogger(Stores.class);

    public static String STORE_NAME = "//*[contains(@content-desc,'%s')]";
    public static final By INFORMED_CUSTOMER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Informed Customer']");
    public static final By STORE_IMAGE_LINK = By.xpath("//android.view.View[@content-desc='Add Store Image']");
    public static final By STORE_IMAGE_SUBMIT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Submit']");

    //public static FlutterElement REMOTE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("customer_selected_popup_take_order_button");
    //public static FlutterElement REPORT_ISSUE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("customer_selected_popup_report_issue_button");
    public static FlutterElement REMOTE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_take_a_remote_order");
    public static FlutterElement RETRY_LOCATION_CAPTURE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_retry_location_capture");
    public static FlutterElement ADD_STORE_IMAGE_LINK = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_add_store_image_card");

    public static void clickOnStore(String storeName) {
        getMobileActions().click(By.xpath(String.format(STORE_NAME, storeName)));
        getMobileActions().clickIfAvailable(INFORMED_CUSTOMER_BUTTON);
    }

    public static void captureStoreImageIfAvailable() {
        try {
            getMobileActions().waitForVisibilityOfElementLocated(STORE_IMAGE_LINK, 1);
            getFlutterActions().click(ADD_STORE_IMAGE_LINK);
            getMobileActions().verifyContextAndSwitchToNativeContext();
            KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
            AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
            driver.pressKey(event);
            //getAndroidActions().click(By.xpath(".//android.widget.TextView[@text='OK']"));
            getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
            getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);
        } catch (Exception ignore) {

        }
    }

    public static void captureStoreImage() {
        getMobileActions().waitForVisibilityOfElementLocated(STORE_IMAGE_LINK, 1);
        getFlutterActions().click(ADD_STORE_IMAGE_LINK);
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        //getAndroidActions().click(By.xpath(".//android.widget.TextView[@text='OK']"));
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);
    }

    public static void clickOnRemoteOrderButton() {
        getFlutterActions().click(REMOTE_ORDER_BUTTON);
    }
}

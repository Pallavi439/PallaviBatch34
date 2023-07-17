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
    public static final By STORE_IMAGE_LINK = By.xpath("//android.view.View[@content-desc='Add Store Image']");
    public static final By STORE_IMAGE_SUBMIT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Submit']");
    public static final By INFORMED_CUSTOMER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Informed Customer']");
    public static String STORE_NAME = "//*[contains(@content-desc,'%s')]";
    public static FlutterElement TAKE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("customer_selected_popup_take_order_button");
    public static FlutterElement REMOTE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_take_a_remote_order");
    public static FlutterElement RETRY_LOCATION_CAPTURE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_retry_location_capture");
    public static FlutterElement ADD_STORE_IMAGE_LINK = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_add_store_image_card");
    public static FlutterElement STORE_SEARCH = getFlutterActions().getFlutterFinder().byValueKey("store_search");
    public static FlutterElement CLICK_STORE = getFlutterActions().getFlutterFinder().byValueKey("beat_store_view_0");
    public static FlutterElement INFORMED_CUSTOMER = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_informed_customer");
    public static FlutterElement REMOTE_ORDER = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_take_a_remote_order");
    static Logger log = LogManager.getLogger(Stores.class);

    public static void clickOnStore(String storeName) throws Exception {
        getUiActions().waitForSeconds(5);
        getFlutterActions().click(STORE_SEARCH);
        getMobileActions().click(By.xpath("//*[@content-desc='Stores']/preceding-sibling::*/*[2]"));
        getMobileActions().type(storeName, By.xpath("//*[@content-desc='Stores']/preceding-sibling::*/*[2]"));
        getUiActions().waitForSeconds(5);
        getFlutterActions().click(CLICK_STORE);
        getUiActions().waitForSeconds(5);
        getMobileActions().clickIfAvailable(INFORMED_CUSTOMER_BUTTON);
        getMobileActions().clickIfAvailable(By.xpath("//android.widget.Button[@content-desc='Yes']"));
    }

    public static void captureStoreImageIfAvailable() {
        try {
            getMobileActions().waitForVisibilityOfElementLocated(STORE_IMAGE_LINK, 1);
            getFlutterActions().click(ADD_STORE_IMAGE_LINK);
            getMobileActions().verifyContextAndSwitchToNativeContext();
            KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
            AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
            driver.pressKey(event);
            getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
            getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);
        } catch (Exception ignore) {
        }
    }

    public static void captureStoreImage() {
        try {
            getUiActions().waitForSeconds(5);
            getFlutterActions().click(ADD_STORE_IMAGE_LINK);
            getMobileActions().verifyContextAndSwitchToNativeContext();
            KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
            AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
            driver.pressKey(event);
            getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
            getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);
        }
        catch (Exception ignore){}
    }

        public static void clickOnRemoteOrderButton () {
            getFlutterActions().click(REMOTE_ORDER_BUTTON);
        }
        public static void clickOnTakeOrderButton () {
            try{getFlutterActions().clickIfAvailable(TAKE_ORDER_BUTTON);}
            catch (Exception ignore){}

        }
    }

package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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
    public static FlutterElement REPORT_ISSUE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("customer_selected_popup_report_issue_button");
    public static FlutterElement MARK_VISIT_LOG= getFlutterActions().getFlutterFinder().byValueKey("mark_visit_log");
    public static final String MARK_VISIT_REASON= "radio_list_tile_%s";
    public static final String MARK_VISIT_REASON_TEXT="(//android.widget.RadioButton[@content-desc])[%s]";
    public static final FlutterElement MARK_VISIT_TEXT_FIELD=getFlutterActions().getFlutterFinder().byValueKey("mark_visit_er_text_field");
    public static FlutterElement MARK_VISIT_SUBMIT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("mark_visit_app_button");
    static Logger log = LogManager.getLogger(Stores.class);

    public static void clickOnStore(String storeName) throws Exception {
        storeName = AutomationUtils.getTestData(storeName);
        getUiActions().waitForSeconds(2);
        getFlutterActions().click(STORE_SEARCH);
        getMobileActions().click(By.xpath("//*[@content-desc='Stores']/preceding-sibling::*/*[2]"));
        getMobileActions().type(storeName, By.xpath("//*[@content-desc='Stores']/preceding-sibling::*/*[2]"));
        getUiActions().waitForSeconds(2);
        getFlutterActions().click(CLICK_STORE);
        getUiActions().waitForSeconds(2);
    }

    public static void informCUSTOMER(){
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
        getFlutterActions().waitForVisibility(ADD_STORE_IMAGE_LINK);
        getFlutterActions().click(ADD_STORE_IMAGE_LINK);
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);

    }

    public static void clickOnRemoteOrderButton() {
        getFlutterActions().click(REMOTE_ORDER_BUTTON);
    }

    public static void clickOnTakeOrderButton() {
        getFlutterActions().click(TAKE_ORDER_BUTTON);
    }

    public static void markVisitCustomer(String reason){
        reason = AutomationUtils.getTestData(reason);
        getFlutterActions().click(REPORT_ISSUE_BUTTON);
        getFlutterActions().waitForVisibility(MARK_VISIT_LOG);
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(MARK_VISIT_REASON,reason)));
        getMarkVisitReason(reason);
        getFlutterActions().type(MARK_VISIT_TEXT_FIELD,AutomationUtils.getTestData("${mark_visit_reason}"));
        getUiActions().waitForSeconds(10);
//        getFlutterActions().click(MARK_VISIT_SUBMIT_BUTTON);
    }
    public static void getMarkVisitReason(String reason){
        getMobileActions().verifyContextAndSwitchToNativeContext();
        reason=String.valueOf((Integer.parseInt(reason) +1));
        WebElement s=getMobileActions().appiumDriver.findElement(By.xpath(String.format(MARK_VISIT_REASON_TEXT,reason)));
        String vist_reason = s.getAttribute("content-desc");
        System.out.println("Visit Reason -> " + vist_reason);
        AutomationUtils.getTestContext().put("mark_visit_reason", vist_reason);
    }
}

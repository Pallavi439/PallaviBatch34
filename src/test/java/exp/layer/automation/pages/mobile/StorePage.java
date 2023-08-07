package exp.layer.automation.pages.mobile;

import com.beust.ah.A;
import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StorePage extends Step {

    static Logger log = LogManager.getLogger(StorePage.class);

    public static final By STORE_IMAGE_LINK = By.xpath("//android.view.View[@content-desc='Add Store Image']");
    public static final By STORE_IMAGE_SUBMIT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Submit']");
    public static final By INFORMED_CUSTOMER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Informed Customer']");
    public static final By REPORT_ISSUE=By.xpath("//*[@content-desc='Report Issue']");

    public static String TAKE_ORDER_BUTTON = "customer_selected_popup_take_order_button";
    public static String REMOTE_ORDER_BUTTON = "beat_plan_take_a_remote_order";
    public static String ADD_STORE_IMAGE_LINK = "beat_plan_add_store_image_card";
    public static String STORE_SEARCH = "store_search";
    public static String CLICK_STORE = "beat_store_view_0";
    public static String REPORT_ISSUE_BUTTON = "customer_selected_popup_report_issue_button";
    public static String MARK_VISIT_LOG = "mark_visit_log";
    public static String MARK_VISIT_REASON = "radio_list_tile_%s";
    public static String MARK_VISIT_REASON_TEXT = "(//android.widget.RadioButton[@content-desc])[%s]";
    public static String MARK_VISIT_TEXT_FIELD = "mark_visit_er_text_field";
    public static String MARK_VISIT_SUBMIT_BUTTON = "mark_visit_app_button";
    public static String STORE_NAME = "store_full_name_0";
    public static String STORE_IMAGE="store_image_0";
    public static String STORE_VERIFIED_ICON="store_verified_1_0";
    public static String STORE_FOCUS_MEDAL="store_focus_medal_0";
    public static String STORE_BRAND_MEDAL="store_brand_medal_0";
    public static String STORE_VALUE_MEDAL="store_value_medal_0";
    public static String STORE_DIRECTION = "store_direction_0";
    public static String STORE_CALL="store_call_0";
    public static String STORE_SEARCH_TEXT_BOX = "beat_plan_search_text_field";
    public static String RETRY_LOCATION_CAPTURE_BUTTON = "beat_plan_retry_location_capture";


    public static void clickOnStore() {
        getMobileActions().flutterClick(CLICK_STORE);
        getMobileActions().waitForSeconds(2);
    }

    public static void searchStore(String storeName) {
        storeName = AutomationUtils.getTestData(storeName);
        getMobileActions().waitForSeconds(2);
        getMobileActions().flutterClick(STORE_SEARCH);
        getMobileActions().flutterType(STORE_SEARCH_TEXT_BOX,storeName);
        getMobileActions().waitForSeconds(2);
    }

    public static void informCUSTOMER() {
        try {
            getMobileActions().waitForVisibilityOfElementLocated(INFORMED_CUSTOMER_BUTTON,5l);
            getMobileActions().click(INFORMED_CUSTOMER_BUTTON);
        }
        catch (Exception ignore){}
        getMobileActions().clickIfAvailable(By.xpath("//android.widget.Button[@content-desc='Yes']"));
    }

    public static void captureStoreImageIfAvailable() {
        try {
            getMobileActions().waitForVisibilityOfElementLocated(STORE_IMAGE_LINK, 8);
            getMobileActions().flutterClick(ADD_STORE_IMAGE_LINK);
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
        getMobileActions().flutterWaitForVisibility(ADD_STORE_IMAGE_LINK);
        getMobileActions().flutterClick(ADD_STORE_IMAGE_LINK);
        getMobileActions().verifyContextAndSwitchToNativeContext();
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);

    }

    public static void clickOnRemoteOrderButton() {
        getMobileActions().flutterClick(REMOTE_ORDER_BUTTON);
    }

    public static void retryLocationCapture(){
        getMobileActions().flutterClick(RETRY_LOCATION_CAPTURE_BUTTON);
    }

    public static void clickOnTakeOrderButton() {
        getMobileActions().flutterClick(TAKE_ORDER_BUTTON);
    }

    public static void markVisitCustomer(String reason) {
        reason = AutomationUtils.getTestData(reason);
        getMobileActions().flutterWaitForVisibility(REPORT_ISSUE_BUTTON);
        getMobileActions().waitForSeconds(2);
//        getMobileActions().flutterClick(REPORT_ISSUE_BUTTON);
        getMobileActions().click(REPORT_ISSUE);
        getMobileActions().flutterWaitForVisibility(MARK_VISIT_LOG);
        getMobileActions().flutterWaitForVisibility(String.format(MARK_VISIT_REASON, reason));
        getMobileActions().flutterClick(String.format(MARK_VISIT_REASON, reason));
        getMarkVisitReason(reason);
        getMobileActions().performScroll();
        getMobileActions().flutterType(MARK_VISIT_TEXT_FIELD, AutomationUtils.getTestData("${mark_visit_reason}"));
        getMobileActions().waitForSeconds(10);
        getMobileActions().flutterClick(MARK_VISIT_SUBMIT_BUTTON);
        getMobileActions().waitForSeconds(5);
    }

    public static void getMarkVisitReason(String reason) {
        getMobileActions().verifyContextAndSwitchToNativeContext();
        reason = String.valueOf((Integer.parseInt(reason) + 1));
        WebElement s = getMobileActions().appiumDriver.findElement(By.xpath(String.format(MARK_VISIT_REASON_TEXT, reason)));
        String vist_reason = s.getAttribute("content-desc");
        System.out.println("Visit Reason -> " + vist_reason);
        AutomationUtils.getTestContext().put("mark_visit_reason", vist_reason);
    }

    public static void visibilityOfStoreListPage() {
        getMobileActions().flutterWaitForVisibility(CLICK_STORE);
    }

    public static void visibilityOfGolfOutletTag() {
        getMobileActions().waitForVisibilityOfElementLocated(AppiumBy.accessibilityId("Automation Gold Outlet"));
    }

    public static void absenceOfSilverOutletTag() {
        getMobileActions().verifyInvisibilityOfElement(AppiumBy.accessibilityId("Automation Silver Outlet"));
    }

    public static void verifyPresenceOfStoreCardIcons(){
        getMobileActions().flutterWaitForVisibility(STORE_NAME);
        getMobileActions().flutterWaitForVisibility(STORE_IMAGE);
        getMobileActions().flutterWaitForVisibility(STORE_VERIFIED_ICON);
        getMobileActions().flutterWaitForVisibility(STORE_FOCUS_MEDAL);
        getMobileActions().flutterWaitForVisibility(STORE_BRAND_MEDAL);
        getMobileActions().flutterWaitForVisibility(STORE_VALUE_MEDAL);
        getMobileActions().flutterWaitForVisibility(STORE_DIRECTION);
        getMobileActions().flutterWaitForVisibility(STORE_CALL);
    }
    public static void verifyStoreDirectionIcon(){
        getMobileActions().flutterClick(STORE_DIRECTION);
        try {
            getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[@text='SKIP']"),10);
            getMobileActions().click(By.xpath("//*[@text='SKIP']"));
        }
        catch (Exception ignore){}
        try {
            getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[@text='Allow']"),10);
            getMobileActions().click(By.xpath("//*[@text='Allow']"));
        }
        catch (Exception ignore){}

        String lati = AutomationUtils.getTestData("${customer_latitude}");
        String longitude =AutomationUtils.getTestData("${customer_longitude}");
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[contains(@text,'"+lati+"') and contains(@text,'"+longitude+"')]"));
        ExpLayerCommonPage.backButton();
    }
    public static void verifyStoreCallIcon(){
        getMobileActions().flutterClick(STORE_CALL);
        String mobno= AutomationUtils.getTestData("${customer_mobile_no}");
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//*[contains(@text,'"+mobno.substring(0,3)+"') and contains(@text,'"+mobno.substring(mobno.length()-4,mobno.length()-1)+"')]"));

        ExpLayerCommonPage.backButton();ExpLayerCommonPage.backButton();ExpLayerCommonPage.backButton();
    }
}

package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Step {

    static Logger log = LogManager.getLogger(CartPage.class);
    public static FlutterElement CART_ICON= getFlutterActions().getFlutterFinder().byValueKey("header_cart_key");
    public static FlutterElement PLACE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("order_details_button");
    public static FlutterElement CART_BACK_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_back_button");
    public static FlutterElement ADD_MORE_ITEMS_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_add_more_item");
    public static FlutterElement PLACE_ORDER_REMOTE_BUTTON=getFlutterActions().getFlutterFinder().byValueKey("place_order_remote_order");
    public static FlutterElement PLACE_ORDER_LOCAL_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("place_order_click_local");
    public static FlutterElement REMOVE_ALL_ITEM_FROM_CART_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_show_delete_all_warning_popup");
    public static FlutterElement EMPTY_CART_POP_UP_YES_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("accept_button");
    public static FlutterElement EMPTY_CART_POP_UP_NO_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("reject_button");
    public static FlutterElement PLACE_ORDER_WITH_STORE_IMAGE=getFlutterActions().getFlutterFinder().byValueKey("work_with_capture_photo_submit_button");

    // public static FlutterElement CART_SHOP_NOW_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("accept_button");

    public static FlutterElement MINIMUM_TIME_SPENT_POPUP=getFlutterActions().getFlutterFinder().byValueKey("cart_minimum_time_spent");
    public static final By EMPTY_CART_TEXT = By.xpath("//android.view.View[@content-desc='Your shopping cart is empty!']");
    public static String ITEMS_COUNT = "//android.view.View[@content-desc='Items %s']";
    public static FlutterElement CART_NEXT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_next_buttom");
    public static FlutterElement ACCEPT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("accept_button");
    public static FlutterElement CUTOFF_TIME=getFlutterActions().getFlutterFinder().byValueKey("cart_warehouse_cutoff_time");
    public static FlutterElement REJECT_BUTTON=getFlutterActions().getFlutterFinder().byValueKey("reject_button");

    public static FlutterElement CART_SHOP_NOW_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("er_info_screen_primary_button");


    public static void removeAllItemFromCartIfAvailable() {
        WebElement we = getMobileActions().waitForVisibilityOfElementLocated(CategoriesPage.BOTTOM_CART_EMPTY_ICON, 2);
        if (we != null) {
            getMobileActions().click(CategoriesPage.BOTTOM_CART_EMPTY_ICON);
            getFlutterActions().click(REMOVE_ALL_ITEM_FROM_CART_BUTTON);
            getFlutterActions().click(EMPTY_CART_POP_UP_YES_BUTTON);
            getFlutterActions().click(CART_SHOP_NOW_BUTTON);
        }
    }

    public static void placeRemoteOrder() throws Exception {
        clickOnCartPageButton();
        clickOnNextButton();
        placeOrderButton();
        clickOnRemoteOrderButton();
        getFlutterActions().waitForVisibility(Stores.CLICK_STORE);
    }

    public static void clickOnNextButton() throws Exception {
        getFlutterActions().click(CART_NEXT_BUTTON);
    }

    public static void placeOrderButton() throws Exception {
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Place Order']"));
        List<WebElement> s = getMobileActions().appiumDriver.findElements(By.xpath("//android.view.View"));
        String grandTotal = s.get(s.size() - 3).getAttribute("content-desc");
        grandTotal = grandTotal.split("\\.")[0];
        grandTotal = grandTotal.replaceAll(",", "");
        grandTotal = AutomationUtils.extractNumbers(grandTotal);
        System.out.println("GRAND_TOTAL_AMOUNT -> " + grandTotal);
        AutomationUtils.getTestContext().put("GRAND_TOTAL_AMOUNT", grandTotal);
        getFlutterActions().click(PLACE_ORDER_BUTTON);
    }
    public static void clickOnRemoteOrderButton() {
        getFlutterActions().click(PLACE_ORDER_REMOTE_BUTTON);
    }
    public static void clickOnCartGoBackButton() {
        getFlutterActions().click(CART_BACK_BUTTON);
    }
    public static void popUpPlaceOrderButton() throws Exception {
        getFlutterActions().click(PLACE_ORDER_LOCAL_BUTTON);
        getMobileActions().waitForSeconds(5);
    }
    public static void clickOnCartPageButton(){
        try {
            getMobileActions().verifyContextAndSwitchToFlutterContext();
            AndroidDriver driver = (AndroidDriver)Step.getMobileActions().appiumDriver;
            driver.executeScript("flutter:waitFor", new Object[]{CART_ICON, 5000});
            getFlutterActions().clickIfAvailable(CART_ICON);
        }
        catch (Exception ignored){}
    }
    public static void removeALLItemFromCart() throws Exception {
        clickOnCartPageButton();
        getFlutterActions().click(REMOVE_ALL_ITEM_FROM_CART_BUTTON);
        getFlutterActions().click(ACCEPT_BUTTON);
        getUiActions().waitForSeconds(4);
        getMobileActions().click(By.xpath("//android.widget.Button[@content-desc='Shop Now']"));
    }
    public static void verifyPresenceOfWarehouseCutoffTime() throws Exception {
        getFlutterActions().waitForVisibility(CUTOFF_TIME);
        getFlutterActions().click(REJECT_BUTTON);
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::*"));
    }
    public static void verifyPresenceOfMinimumTimeSpent(){
        getFlutterActions().waitForVisibility(MINIMUM_TIME_SPENT_POPUP);
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("reject_button"));
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::*"));
    }

    public static void placeLocalOrder() throws Exception {
        clickOnCartPageButton();
        clickOnNextButton();
        placeOrderButton();
        popUpPlaceOrderButton();

        ExpLayerCommonPage.captureImage();

        getUiActions().waitForSeconds(3);
        getFlutterActions().click(PLACE_ORDER_WITH_STORE_IMAGE);
        getFlutterActions().waitForVisibility(Stores.CLICK_STORE);
    }
}

package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Step {

    static Logger log = LogManager.getLogger(CartPage.class);

    public static String ITEMS_COUNT = "//android.view.View[@content-desc='Items %s']";
    public static By CART_BACK_BUTTON = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
    public static String CART_ICON = "header_cart_key";
    public static String PLACE_ORDER_BUTTON = "order_details_button";
    public static String ADD_MORE_ITEMS_BUTTON = "cart_add_more_item";
    public static String POP_UP_PLACE_ORDER_BUTTON = "place_order_click_local";
    public static String PLACE_ORDER_REMOTE_BUTTON = "place_order_remote_order";
    public static String REMOVE_ALL_ITEM_FROM_CART_BUTTON = "cart_show_delete_all_warning_popup";
    public static String EMPTY_CART_POP_UP_YES_BUTTON = "accept_button";
    public static String EMPTY_CART_POP_UP_NO_BUTTON = "reject_button";
    public static String PLACE_ORDER_WITH_STORE_IMAGE = "work_with_capture_photo_submit_button";
    public static String MINIMUM_TIME_SPENT_POPUP = "cart_minimum_time_spent";
    public static String CART_NEXT_BUTTON = "cart_next_buttom";
    public static String ACCEPT_BUTTON = "accept_button";
    public static String CUTOFF_TIME = "cart_warehouse_cutoff_time";
    public static String REJECT_BUTTON = "reject_button";
    public static String CART_SHOP_NOW_BUTTON = "er_info_screen_primary_button";
    public static String REMOVE_INDEX1_ITEM = "cart_item_list_er_item_card_1";
    public static String DECREMENT_BUTTON = "cart_item_1_decrement_button_0";


    public static void removeAllItemFromCartIfAvailable() {
        WebElement we = getMobileActions().waitForVisibilityOfElementLocated(CategoriesPage.BOTTOM_CART_EMPTY_ICON, 2);
        if (we != null) {
            getMobileActions().click(CategoriesPage.BOTTOM_CART_EMPTY_ICON);
            getMobileActions().flutterClick(REMOVE_ALL_ITEM_FROM_CART_BUTTON);
            getMobileActions().flutterClick(EMPTY_CART_POP_UP_YES_BUTTON);
            getMobileActions().flutterClick(CART_SHOP_NOW_BUTTON);
        }
    }

    //remote order
    public static void placeRemoteOrder() {
        clickOnNextButton();
        placeOrderButton();
        clickOnRemoteOrderButton();
        getMobileActions().flutterWaitForVisibility(StorePage.CLICK_STORE);
    }

    //if location is same
    public static void placeOrderWithLocal() {
        clickOnNextButton();
        placeOrderButton();
        popUpPlaceOrderButton();
        getMobileActions().flutterWaitForVisibility(StorePage.CLICK_STORE);
    }

    //if location is different
    public static void placeLocalOrder() {
        clickOnNextButton();
        placeOrderButton();
        popUpPlaceOrderButton();
        ExpLayerCommonPage.captureImage();
        getMobileActions().waitForSeconds(2);
        getMobileActions().flutterClick(PLACE_ORDER_WITH_STORE_IMAGE);
        getMobileActions().flutterWaitForVisibility(StorePage.CLICK_STORE);
    }

    public static void clickOnNextButton() {
        getMobileActions().flutterClick(CART_NEXT_BUTTON);
    }

    public static void placeOrderButton() {
        getGrandTotal();
        getMobileActions().flutterClick(PLACE_ORDER_BUTTON);
    }

    public static void clickOnRemoteOrderButton() {
        getMobileActions().flutterClick(PLACE_ORDER_REMOTE_BUTTON);
    }

    public static void clickOnCartGoBackButton() {
        //getFlutterActions().click(CART_BACK_BUTTON);
        getMobileActions().click(CART_BACK_BUTTON);
    }

    public static void popUpPlaceOrderButton() {
        getMobileActions().flutterClick(POP_UP_PLACE_ORDER_BUTTON);
        getMobileActions().waitForSeconds(5);
    }

    public static void getGrandTotal() {
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Place Order']"));
        getMobileActions().verifyContextAndSwitchToNativeContext();
        List<WebElement> s = getMobileActions().appiumDriver.findElements(By.xpath("//android.view.View"));
        String grandTotal = s.get(s.size() - 3).getAttribute("content-desc");
        grandTotal = grandTotal.split("\\.")[0];
        grandTotal = grandTotal.replaceAll(",", "");
        grandTotal = AutomationUtils.extractNumbers(grandTotal);
        System.out.println("GRAND_TOTAL_AMOUNT -> " + grandTotal);
        AutomationUtils.getTestContext().put("GRAND_TOTAL_AMOUNT", grandTotal);
    }

    public static void clickOnPlaceOrderButtonRemoteOrderPopup() {
        getMobileActions().flutterClick(PLACE_ORDER_BUTTON);
        getMobileActions().flutterClick(PLACE_ORDER_REMOTE_BUTTON);
        getMobileActions().flutterWaitForVisibility(StorePage.CLICK_STORE);
    }

    public static void clickOnAddMoreItemButtonFromCartPage() {
        getMobileActions().flutterClick(ADD_MORE_ITEMS_BUTTON);
        getMobileActions().waitForSeconds(5);
    }

    public static void clickOnCartPageButton() {
        getMobileActions().flutterClick(CART_ICON);
    }

    public static void removeALLItemFromCart() {
        getMobileActions().flutterClick(REMOVE_ALL_ITEM_FROM_CART_BUTTON);
        getMobileActions().flutterClick(ACCEPT_BUTTON);
        getMobileActions().waitForSeconds(4);
        getMobileActions().flutterClick(CART_SHOP_NOW_BUTTON);
    }

    public static void verifyPresenceOfWarehouseCutoffTime() {
        getMobileActions().flutterWaitForVisibility(CUTOFF_TIME);
        getMobileActions().flutterClick(REJECT_BUTTON);
        getMobileActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::*"));
    }

    public static void verifyPresenceOfMinimumTimeSpent() {
        getMobileActions().flutterWaitForVisibility(MINIMUM_TIME_SPENT_POPUP);
        getMobileActions().flutterClick("reject_button");
        getMobileActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::*"));
    }

    public static void removeItemFromTheCartPage() {
        getMobileActions().flutterClick(REMOVE_INDEX1_ITEM);
    }

    public static void waitOnCartPage(int wait_time) {
        while (wait_time != 0) {
            if (wait_time > 50) {
                wait_time = wait_time - 50;
                getMobileActions().waitForSeconds(47);
            } else {
                getMobileActions().waitForSeconds(wait_time);
                wait_time = 0;
            }
            getMobileActions().flutterClick(CART_NEXT_BUTTON);
            getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::*"));
        }
    }

    public static void decrementItemQtyFromCartPage() {
        getMobileActions().performScroll();
        getMobileActions().flutterClick(DECREMENT_BUTTON);
    }
}

package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Step {

    static Logger log = LogManager.getLogger(CartPage.class);


    public static FlutterElement CART_BACK_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_back_button");
    public static FlutterElement ADD_MORE_ITEMS_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_add_more_item");
    public static FlutterElement NEXT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_next_buttom");
    public static FlutterElement POP_UP_PLACE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("place_order_click_local");
    public static FlutterElement POPUP_REMOTE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("place_order_remote_order");
    public static FlutterElement REMOVE_ALL_ITEM_FROM_CART_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_show_delete_all_warning_popup");

    public static FlutterElement EMPTY_CART_POP_UP_YES_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("accept_button");
    public static FlutterElement EMPTY_CART_POP_UP_NO_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("reject_button");
    public static FlutterElement CART_SHOP_NOW_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("er_info_screen_primary_button");

    public static final By PLACE_ORDER_BUTTON = new AppiumBy.ByAccessibilityId("Place Order");

    public static void removeAllItemFromCartIfAvailable() {
        WebElement we = getMobileActions().waitForVisibilityOfElementLocated(CategoriesPage.BOTTOM_CART_EMPTY_ICON, 2);
        if (we != null) {
            getMobileActions().click(CategoriesPage.BOTTOM_CART_EMPTY_ICON);
            getFlutterActions().click(REMOVE_ALL_ITEM_FROM_CART_BUTTON);
            getFlutterActions().click(EMPTY_CART_POP_UP_YES_BUTTON);
            getFlutterActions().click(CART_SHOP_NOW_BUTTON);
        }
    }

    public static void clickOnNextButton() throws Exception {
        getFlutterActions().click(NEXT_BUTTON);
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
    }

    public static void clickOnRemoteOrderButton() {

    }

    public static void clickOnCartGoBackButton() {
        getFlutterActions().click(CART_BACK_BUTTON);
    }

    public static void popUpPlaceOrderButton() throws Exception {
        getMobileActions().click(PLACE_ORDER_BUTTON);
        getFlutterActions().click(POP_UP_PLACE_ORDER_BUTTON);
        getMobileActions().waitForSeconds(5);
    }

    public static void getGrandTotal() {
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
        getMobileActions().click(PLACE_ORDER_BUTTON);
        getFlutterActions().click(POPUP_REMOTE_ORDER_BUTTON);
    }

    public static void clickOnAddMoreItemButtonFromCartPage() {
        getFlutterActions().click(ADD_MORE_ITEMS_BUTTON);

    }
}

package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Step {

    public static FlutterElement ADD_MORE_ITEMS_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_add_more_item");
    public static FlutterElement NEXT_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("cart_next_buttom");
    public static FlutterElement PLACE_ORDER_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("order_details_button");
    public static FlutterElement MRP = getFlutterActions().getFlutterFinder().byValueKey("mrp");


    //public static final By ADD_MORE_ITEMS_BUTTON = By.xpath("//android.widget.Button[@content-desc='Add More Items']");
    //public static final By NEXT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Next']");
    //public static final By PLACE_ORDER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Place Order']");
    public static final By REMOTE_ORDER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Remote Order']");
    public static final By CLICK_IF_LOCAL_BUTTON = By.xpath("//android.widget.Button[@content-desc='Click If Local']");


    public static void clickOnNextButton() throws Exception {
        getFlutterActions().click(NEXT_BUTTON);
    }

    public static void placeOrderButton() throws Exception {
        getMobileActions().waitForVisibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Place Order']"));
        List<WebElement> s = getMobileActions().appiumDriver.findElements(By.xpath("//android.view.View"));
        for (WebElement we : s) {
            System.out.println(">>>>>>>>>>>" + we.getAttribute("content-desc"));
        }
        getFlutterActions().click(PLACE_ORDER_BUTTON);
    }

    public static void clickOnRemoteOrderButton() throws Exception {
        getMobileActions().click(REMOTE_ORDER_BUTTON);
        getMobileActions().waitForSeconds(3);
    }


}

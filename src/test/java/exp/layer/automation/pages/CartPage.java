package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Step {

    public static final By ADD_MORE_ITEMS_BUTTON = By.xpath("//android.widget.Button[@content-desc='Add More Items']");
    public static final By NEXT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Next']");
    public static final By PLACE_ORDER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Place Order']");
    public static final By REMOTE_ORDER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Remote Order']");
    public static final By CLICK_IF_LOCAL_BUTTON = By.xpath("//android.widget.Button[@content-desc='Click If Local']");


    public static void clickOnNextButton() throws Exception {
        getMobileActions().click(NEXT_BUTTON);
    }

    public static void placeOrderButton() throws Exception {
        getMobileActions().waitForVisibilityOfElementLocated(PLACE_ORDER_BUTTON);
        List<WebElement> s = getMobileActions().appiumDriver.findElements(By.xpath("//android.view.View"));
       /* for(WebElement we:s){
            System.out.println(">>>>>>>>>>>"+we.getAttribute("content-desc"));
        }*/
        getMobileActions().click(PLACE_ORDER_BUTTON);
    }

    public static void clickOnRemoteOrderButton() throws Exception {
        getMobileActions().click(REMOTE_ORDER_BUTTON);
        getMobileActions().waitForSeconds(3);
    }


}

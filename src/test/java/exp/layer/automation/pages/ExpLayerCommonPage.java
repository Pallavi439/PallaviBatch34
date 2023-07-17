package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ExpLayerCommonPage extends Step {

    static Logger log = LogManager.getLogger(ExpLayerCommonPage.class);

    public static void login() {

    }

    public static void selectLanguage() {

    }

    public static void permissionToLocation() {

    }

    public static void saveCartItemQuantity(String item_name, String current_quantity) {
        getUiActions().saveTestData("cart_" + item_name, current_quantity);
    }

    public static String getCartItemQuantity(String item_name) {
        String temp_quantity;
        try {
            temp_quantity = getUiActions().getTestData("cart_" + item_name);
            return temp_quantity;
        } catch (Exception e) {
            return "0";
        }
    }public static void verifyOrderTimer() throws Exception {
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getUiActions().waitForSeconds(2);
        getMobileActions().click(By.xpath("(//*[contains(@content-desc,'Categories')]/preceding-sibling::*)[1]"));
        getMobileActions().waitForSeconds(2);
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getMobileActions().waitForSeconds(20);
        getMobileActions().verifyVisibilityOfWebElement(By.xpath("//*[contains(@content-desc,'01')]"));
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("beat_plan_search_back"));
        getUiActions().waitForSeconds(2);
    }
    public void placeNormalOrder() {
    }

    public static void log_out(){

    }


}

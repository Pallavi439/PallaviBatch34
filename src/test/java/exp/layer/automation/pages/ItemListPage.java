package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ItemListPage extends Step {

    public static final By VIEWS_ANDROID = AppiumBy.accessibilityId("Views");
    public static final By VIEWS_IOS = By.xpath("//*[@name='Views']");

    public static void addOneRandomItemToCart() throws Exception {
        int categoryNumber = AutomationUtils.generateRandomNoByRange(1, 6);
        getMobileActions().click(By.xpath("(//android.widget.Button[@content-desc=\"Add\"])[" + categoryNumber + "]"));
    }


}

package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.openqa.selenium.By;

public class LocalityPage extends Step {

    public static String LOCALITY = "//*[contains(@content-desc,'%s')]";
    public static FlutterElement LOCALITY1 = getFlutterActions().getFlutterFinder().byValueKey("beat_plan_locatities_list0");

    public static void resetLocalityAndStore(String locality) throws Exception {
        clickOnLocalityAndCustomer(locality, "l");
        Stores.clickOnTakeOrderButton();
        getUiActions().waitForSeconds(3);
        getFlutterActions().clickIfAvailable(getFlutterActions().getFlutterFinder().byValueKey("work_with_capture_photo_back_button"));
        getMobileActions().clickIfAvailable(By.xpath("(//*[contains(@content-desc,'Take a Remote')]/preceding-sibling::*)[1]/*"));

        getUiActions().waitForSeconds(3);
        getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("beat_plan_search_back"));
        getUiActions().waitForSeconds(3);
    }

    public static void clickOnLocalityAndCustomer(String locality, String store) throws Exception {
        locality = AutomationUtils.getTestData(locality);
        store = AutomationUtils.getTestData(store);
        getMobileActions().click(By.xpath(String.format(LOCALITY, locality)));
        Stores.clickOnStore(store);
    }
}

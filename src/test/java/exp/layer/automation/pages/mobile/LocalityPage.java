package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import org.openqa.selenium.By;

import java.util.List;

public class LocalityPage extends Step {

    public static String LOCALITY = "//*[contains(@content-desc,'%s')]";
    public static String ORDER_PLACED="store_reason_0";

    public static void clickOnLocalityAndCustomer(String locality, String store) {
        clickOnLocality(locality);
        StorePage.searchStore(store);
        StorePage.clickOnStore();
        StorePage.informCUSTOMER();
    }

    public static void clickOnLocality(String locality) {
        locality = AutomationUtils.getTestData(locality);
        getMobileActions().click(By.xpath(String.format(LOCALITY, locality)));
        getMobileActions().waitForSeconds(2);
    }

    public static void markVisitStore(String locality, List<List<String>> mark_visit_details) {
        clickOnLocality(locality);
        for (List<String> mark_visit_store_reason : mark_visit_details) {
            StorePage.searchStore(mark_visit_store_reason.get(0));
            StorePage.clickOnStore();
            StorePage.captureStoreImageIfAvailable();
            StorePage.markVisitCustomer(mark_visit_store_reason.get(1));
        }
    }

    public static void verifyVisibilityOfOrderPlacedIcon(String store_name){
        getMobileActions().waitForSeconds(5);
        StorePage.searchStore(store_name);
        getMobileActions().flutterWaitForVisibility(ORDER_PLACED);
    }
}

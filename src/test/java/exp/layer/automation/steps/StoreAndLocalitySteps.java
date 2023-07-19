package exp.layer.automation.steps;

import exp.layer.automation.pages.LocalityPage;
import exp.layer.automation.pages.StorePage;
import io.cucumber.java.en.Given;

import java.util.List;

public class StoreAndLocalitySteps {
    @Given("{word} mark visit {string} locality store")
    public static void mark_visit_locality_store_with_reason(String word, String locality, List<List<String>> mark_visit_details) throws Exception {
        LocalityPage.markVisitStore(locality,mark_visit_details);
    }
    @Given("{word} verifies visibility of Store list page")
    public static void verifies_visibility_of_store_lst_page(String word){
        StorePage.visibilityOfStoreListPage();
    }
}

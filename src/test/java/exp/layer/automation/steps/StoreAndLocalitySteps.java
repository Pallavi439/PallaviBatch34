package exp.layer.automation.steps;

import exp.layer.automation.pages.LocalityPage;
import io.cucumber.java.en.Given;

public class StoreAndLocalitySteps {
    @Given("{word} mark visit {string} locality {string} store with reason {string}")
    public static void mark_visit_locality_store_with_reason(String word, String locality,String store, String reason) throws Exception {
        LocalityPage.markVisitStore(locality,store,reason);
    }
}

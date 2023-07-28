package exp.layer.automation.steps.mobile;

import exp.layer.automation.pages.mobile.LocalityPage;
import exp.layer.automation.pages.mobile.StorePage;
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

    @Given("{word} clicks on locality {string}")
    public static void clicks_on_locality(String word, String locality_name){
        LocalityPage.clickOnLocality(locality_name);
    }
    @Given("{word} searches store {string}")
    public static void searches_store(String word, String store_name){
        StorePage.searchStore(store_name);
    }
    @Given("{word} verifies presence of gold outlet tag")
    public static void verify_presence_of_gold_outlet_tag(String word){
        StorePage.visibilityOfGolfOutletTag();
    }
    @Given("{word} verifies absence of silver outlet tag")
    public static void verify_absence_of_silver_outlet_tag(String word){
        StorePage.absenceOfSilverOutletTag();
    }

    @Given("{word} verifies store card icons visibility")
    public static void verify_presence_of_store_card_icons(String word){
        StorePage.verifyPresenceOfStoreCardIcons();
    }

    @Given("{word} verifies store direction icon")
    public static void verify_store_direction_icon(String word){
        StorePage.verifyStoreDirectionIcon();
    }
    @Given("{word} verifies store call icon")
    public static void verify_store_call_icon(String word){
        StorePage.verifyStoreCallIcon();
    }
}
package exp.layer.automation.steps.mobile;

import exp.layer.automation.pages.mobile.CategoriesPage;
import io.cucumber.java.en.Given;

import java.util.List;

public class CategoriesPageSteps {
    @Given("{word} add item to cart")
    public void add_item_to_cart(String word, List<List<String>> item_list) throws Exception {
        CategoriesPage.addItemToCart(item_list);
    }

    @Given("{word} wait for visibility of catalogue")
    public void wait_for_visibility_of_catalogue(String word) {
        CategoriesPage.waitForVisibilityOfCatalogue();
    }

    @Given("{word} verify recent search for {string}")
    public void verify_recent_search_for(String word,String item_type){
        CategoriesPage.verifyRecentSearch(item_type);
    }

    @Given("{word} verify all items icon functionality")
    public void verify_all_items_icon_functionality(String word){
        CategoriesPage.verifyAllItemsIconFunctionality();
    }
}

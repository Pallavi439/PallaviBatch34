package exp.layer.automation.steps.mobile;

import exp.layer.automation.pages.mobile.CategoriesPage;
import io.cucumber.java.en.Given;

import java.util.List;

public class CategoriesPageSteps {
    @Given("{word} add item to cart")
    public static void add_item_to_cart(String word, List<List<String>> item_list) throws Exception {
        CategoriesPage.addItemToCart(item_list);
    }
}

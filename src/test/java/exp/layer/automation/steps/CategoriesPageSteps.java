package exp.layer.automation.steps;

import er.automation.engine.helpers.AutomationUtils;
import exp.layer.automation.pages.*;
import io.cucumber.java.en.Given;

import java.util.List;

public class CategoriesPageSteps {
    @Given("{word} add item to cart")
    public static void add_item_to_cart(String word, List<List<String>> item_list) throws Exception {
        CategoriesPage.addItemToCart(item_list);
    }

    @Given("{word} add items to cart")
    public static void add_items_to_cart(String word,List<List<String>> item_list) throws Exception
    {CategoriesPage.addItemsToCart(item_list);}
}

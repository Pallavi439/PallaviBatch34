package exp.layer.automation.steps.mobile;

import exp.layer.automation.pages.mobile.ItemListPage;
import io.cucumber.java.en.Given;

import java.util.List;

public class ItemListPageSteps {
    @Given("{word} increase item quantity")
    public void increase_item_quantity(String word, List<List<String>> item_list) throws Exception {
        ItemListPage.increaseItemQuantity(item_list);
    }

    @Given("{word} decrease item quantity")
    public void decrease_item_quantity(String word, List<List<String>> item_list) throws Exception {
        ItemListPage.decreaseItemQuantity(item_list);
    }

    @Given("{word} verifies all bottom icons")
    public void verify_all_bottom_icon(String word){
        ItemListPage.verifyBottomIcons();
    }
}

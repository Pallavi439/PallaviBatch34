package exp.layer.automation.steps;

import exp.layer.automation.pages.*;
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
}

package exp.layer.automation.steps;

import exp.layer.automation.pages.*;
import io.cucumber.java.en.Given;

public class CartPageSteps {
    @Given("{word} verifies total number of items in cart")
    public static void verifies_total_items_in_cart(String word) throws Exception {
        CartPage.verifyTotalItemCount();
    }

    @Given("{word} removes all item from cart")
    public static void remove_item_from_cart(String word) throws Exception {
        CartPage.removeALLItemFromCart();
    }

    @Given("{word} place remote order")
    public static void place_remote_order(String word) throws Exception {
        CartPage.placeRemoteOrder();
    }
}

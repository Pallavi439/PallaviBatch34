package exp.layer.automation.steps;

import exp.layer.automation.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CartPageSteps {
    @Given("{word} removes all item from cart")
    public static void remove_item_from_cart(String word) throws Exception {
        CartPage.removeALLItemFromCart();
    }

    @Given("{word} place remote order")
    public static void place_remote_order(String word) throws Exception {
        CartPage.placeRemoteOrder();
    }

    @Given("{word} click on cart button")
    public static void click_on_cart_button(String word) throws Exception{
        CartPage.clickOnCartPageButton();
    }
    @Given("{word} verifies presence of minimum time spent")
    public static void verify_minimum_time_spent_presence(String word){
        CartPage.verifyPresenceOfMinimumTimeSpent();
    }

    @And("user remove item from the cart page")
    public void userRemoveItemFromTheCartPage() {
        CartPage.removeItemFromTheCartPage();
    }
}

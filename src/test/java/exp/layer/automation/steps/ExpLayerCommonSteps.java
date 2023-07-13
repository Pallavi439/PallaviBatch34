package exp.layer.automation.steps;

import exp.layer.automation.pages.*;
import io.cucumber.java.en.Given;

import java.util.List;

public class ExpLayerCommonSteps {

    @Given("{word} login to the experience layer sales app with valid details")
    public void experience_layer_sales_dev_login(String word, List<String> list) throws Exception {
        LoginPage.salesAppLoginByUserDetails(list.get(0), list.get(1));
    }

    @Given("{word} click on beat button")
    public void click_on_available_beat_button(String word) throws Exception {
        DashboardPage.clickOnBeatButton();
    }

    @Given("{word} click on locality and store")
    public void click_on_locality(String word, List<String> list) throws Exception {
        LocalityPage.clickOnLocalityAndCustomer(list.get(0), list.get(1));
    }

    @Given("{word} captures store image")
    public void capture_store_image(String word) throws Exception {
        Stores.captureStoreImage();
    }

    @Given("{word} clicks on take a remote order button")
    public void take_a_remote_order(String word) throws Exception {
        Stores.clickOnRemoteOrderButton();
    }

    @Given("{word} add one random item to cart")
    public void add_item_cart(String word) throws Exception {
        CategoriesPage.addRandomOneItemToCart();
    }

    @Given("{word} add item to cart")
    public void add_item_cart(String word, List<List<String>> list) throws Exception {
        CategoriesPage.productSearch(list);
    }

    @Given("{word} click on cart next button")
    public void click_on_cart_next_button(String word) throws Exception {
        CartPage.clickOnNextButton();
    }

    @Given("{word} click on place order button")
    public void click_on_cart_place_order_button(String word) throws Exception {
        CartPage.placeOrderButton();
    }

    @Given("{word} click on cart remote order button")
    public void click_on_cart_remote_order_button(String word) throws Exception {
        CartPage.clickOnRemoteOrderButton();
    }
}

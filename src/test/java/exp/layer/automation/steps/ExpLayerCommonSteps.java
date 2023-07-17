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
    @Given("{word} captures store image if available")
    public void capture_store_image_if_available(String word) throws Exception {
        Stores.captureStoreImageIfAvailable();
    }

    @Given("{word} click on take order button")
    public void click_on_take_order_button(String word){
        Stores.clickOnTakeOrderButton();
    }

    @Given("{word} reset store for {string}")
    public void reset_store(String word,String locality) throws Exception {
        LocalityPage.resetLocalityAndStore(locality);

    }@Given("{word} captures store image")
    public void capture_store_image(String word) throws Exception {
        Stores.captureStoreImage();
    }
    @Given("{word} clicks on take a remote order button")
    public void take_a_remote_order(String word) throws Exception {
        Stores.clickOnRemoteOrderButton();
    }
    @Given("{word} add one random item to cart")
    public void add_random_category_item_cart(String word) throws Exception {
        CategoriesPage.addRandomCategoryItem();
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


    @Given("{word} click on cart local place order button")
    public void click_on_cart_local_order_button(String word) throws Exception {
        CartPage.popUpPlaceOrderButton();
    }

    @Given("{word} click on cart back button")
    public void click_on_cart_back_button(String word) {
        CartPage.clickOnCartGoBackButton();
    }

    @Given("{word} click on order constraints strip")
    public void click_oc_strip(String word) {
        OcPage.clickOnOcStrip();
    }

    @Given("{word} click on order constraints title {string}")
    public void click_oc_title(String word, String index) {
        OcPage.clickOnOcTitle(index);
    }

    @Given("{word} verify absence of order constraint strip")
    public void verify_absence_of_oc_strip(String word) throws Exception {
        OcPage.verifyAbsenceOfOcStrip();
    }
    @Given("{word} verifies order timer")
    public void verify_order_timer(String word) throws Exception {
        ExpLayerCommonPage.verifyOrderTimer();
    }
    @Given("{word} verifies presence of cart warehouse cutoff time")
    public void verify_presence_of_cuttoff_popup (String word) throws Exception {
        CartPage.verifyPresenceOfWarehouseCutoffTime();
    }
}

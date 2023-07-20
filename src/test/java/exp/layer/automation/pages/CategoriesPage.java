package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class CategoriesPage extends Step {

    static Logger log = LogManager.getLogger(CategoriesPage.class);

    public static By BOTTOM_CART_EMPTY_ICON = By.xpath("//android.view.View[@content-desc='Cart Tab 4 of 4']/android.widget.ImageView");
    public static FlutterElement CATEGORIES_SEARCH = getFlutterActions().getFlutterFinder().byValueKey("header_search_key");
    public static FlutterElement CATEGORIES_SEARCH_RESULTS = getFlutterActions().getFlutterFinder().byValueKey("search_er_input_box");
    public static FlutterElement BOTTOM_CART_ICON = getFlutterActions().getFlutterFinder().byValueKey("header_cart_key");
    public static String PRODUCT_CART_ADD_BUTTON = "product_%s_add_button_%s";
    public static String PRODUCT_CART_INCREMENT_BUTTON = "product_%s_increment_button_%s";
    public static final By ADD_BUTTON_PIECE = By.xpath("(//android.widget.Button[@content-desc='Add'])[1]");
    public static FlutterElement SEARCH_ITEM = getFlutterActions().getFlutterFinder().byValueKey("header_search_key");
    public static FlutterElement ITEM_SEARCH_BOX = getFlutterActions().getFlutterFinder().byValueKey("search_er_input_box");
    public static FlutterElement CLICK_ITEM_INDEX_0 = getFlutterActions().getFlutterFinder().byValueKey("search_item_click_list_tile_0");
    public int categoryNumber = AutomationUtils.generateRandomNoByRange(1, 4);



    public static void addItemToCart(List<List<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> data = AutomationUtils.replaceListData(list.get(i));
            getFlutterActions().click(CATEGORIES_SEARCH);
            getFlutterActions().type(CATEGORIES_SEARCH_RESULTS, data.get(0));
            getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("search_item_click_list_tile_" + data.get(1)));
            int index;
            if (data.get(2).equalsIgnoreCase("Piece")) {
                index = 0;
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_ADD_BUTTON, 0, index)));
            } else if (data.get(2).equalsIgnoreCase("Bag")) {
                index = 1;
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_ADD_BUTTON, 0, index)));
            } else if (data.get(2).equalsIgnoreCase("Case")) {
                index = 2;
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_ADD_BUTTON, 0, index)));
            } else {
                index = 0;
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_ADD_BUTTON, 0, index)));
            }

            for (int j = 0; j < Integer.parseInt(data.get(3)); j++) {
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_INCREMENT_BUTTON, 0, index)));
            }
            getFlutterActions().click(BOTTOM_CART_ICON);
            if (list.size() - 1 != i) {
                getFlutterActions().click(CartPage.ADD_MORE_ITEMS_BUTTON);
            }
        }
    }
    public static void clickOneRandomCategory() throws Exception {
        int categoryNumber = AutomationUtils.generateRandomNoByRange(1, 4);
        getMobileActions().click(By.xpath("//android.view.View[@content-desc='Automation-Category-" + categoryNumber + "']/android.widget.ImageView"));
    }

    public static void searchItemDetails(String itemDetails) throws Exception {
        searchItem(itemDetails);
        getUiActions().waitForSeconds(1);
        getFlutterActions().click(CLICK_ITEM_INDEX_0);
        getUiActions().waitForSeconds(1);
    }

    public static void searchItem(String itemDetails) {
        getMobileActions().waitForSeconds(3);
        getFlutterActions().click(SEARCH_ITEM);
        getFlutterActions().type(ITEM_SEARCH_BOX, itemDetails);
        getUiActions().waitForSeconds(1);
    }

    public static void addRandomOneItemToCart() throws Exception {
        int itemNo = AutomationUtils.generateRandomNoByRange(1, 38);
        String itemDetails = "Automation-Test-Item-Name-" + itemNo + "";
        searchItemDetails(itemDetails);
        getMobileActions().click(ADD_BUTTON_PIECE);
        ExpLayerCommonPage.saveCartItemQuantity(itemDetails, "1");
        String items_count = ExpLayerCommonPage.getCartItemQuantity("items");
        ExpLayerCommonPage.saveCartItemQuantity("item" + String.valueOf(Integer.parseInt(items_count) + 1), itemDetails);
        ExpLayerCommonPage.saveCartItemQuantity("items", String.valueOf(Integer.parseInt(items_count) + 1));
    }

    public static void addRandomCategoryItem() throws Exception {
        clickOneRandomCategory();
        addRandomOneItemToCart();
    }

    public static void addItemsToCart(List<List<String>> item_list) throws Exception {
        if (item_list.get(0).size() == 1) {
            for (List<String> item_detail : item_list) {
                addItem(item_detail.get(0));
            }
        } else if (item_list.get(0).size() == 2) {
            for (List<String> item_detail : item_list) {
                addItem(item_detail.get(0), item_detail.get(1));
            }
        } else if (item_list.get(0).size() == 3) {
            for (List<String> item_detail : item_list) {
                addItem(item_detail.get(0), item_detail.get(1), item_detail.get(2));
            }
        }
    }
    public static void addItem(String item_detail) throws Exception {
        addItem(item_detail, "Bag");
    }

    public static void addItem(String item_detail, String item_type) throws Exception {
        addItem(item_detail, item_type, "1");
    }

    public static void addItem(String item_detail, String item_type, String item_quantity) throws Exception {
        if (item_detail.contains("Category")) {
            searchItem(item_detail);
            int catNo = AutomationUtils.generateRandomNoByRange(0, 4);
            String clickon = "search_item_click_list_tile_" + catNo;
            FlutterElement f_clickon = getFlutterActions().getFlutterFinder().byValueKey(clickon);
            getFlutterActions().click(f_clickon);
            getMobileActions().waitForSeconds(10);
        } else {
            item_detail = item_detail.concat(" @");
            searchItemDetails(item_detail);
        }
        if (item_type.equalsIgnoreCase("Bag")) {
            getFlutterActions().getFlutterFinder().byValueKey("product_0_add_button_1").click();
            if (Integer.parseInt(item_quantity) > 1) {
                for (int i = 1; i < Integer.parseInt(item_quantity); i++) {
                    getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("product_0_increment_button_1"));
                }
            }
        } else if (item_type.equalsIgnoreCase("Case")) {
            getFlutterActions().getFlutterFinder().byValueKey("product_0_add_button_2").click();
            if (Integer.parseInt(item_quantity) > 1) {
                for (int i = 1; i < Integer.parseInt(item_quantity); i++) {
                    getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("product_0_increment_button_2"));
                }
            }
        } else {
            getFlutterActions().getFlutterFinder().byValueKey("product_0_add_button_0").click();
            if (Integer.parseInt(item_quantity) > 1) {
                for (int i = 1; i < Integer.parseInt(item_quantity); i++) {
                    getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey("product_0_increment_button_0"));
                }
            }
        }

    }
}

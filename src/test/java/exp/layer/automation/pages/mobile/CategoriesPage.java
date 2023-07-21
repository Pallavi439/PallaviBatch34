package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class CategoriesPage extends Step {

    public static By BOTTOM_CART_EMPTY_ICON = By.xpath("//android.view.View[@content-desc='Cart Tab 4 of 4']/android.widget.ImageView");
    public static By ADD_BUTTON_PIECE = By.xpath("(//android.widget.Button[@content-desc='Add'])[1]");
    public static String CATEGORIES_SEARCH = "header_search_key";
    public static String CATEGORIES_SEARCH_RESULTS = "search_er_input_box";
    public static String BOTTOM_CART_ICON = "header_cart_key";
    public static String PRODUCT_CART_ADD_BUTTON = "product_%s_add_button_%s";
    public static String PRODUCT_CART_INCREMENT_BUTTON = "product_%s_increment_button_%s";
    public static String SEARCH_ITEM = "header_search_key";
    public static String ITEM_SEARCH_BOX = "search_er_input_box";
    public static String CLICK_ITEM_INDEX_0 = "search_item_click_list_tile_0";
    static Logger log = LogManager.getLogger(CategoriesPage.class);
    public static String CATEGORY = "category_%s";

    public static void addItemToCart(List<List<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> data = AutomationUtils.replaceListData(list.get(i));
            getMobileActions().flutterWaitForVisibility(CATEGORIES_SEARCH);
            getMobileActions().flutterClick(CATEGORIES_SEARCH);
            getMobileActions().flutterWaitForVisibility("search_section_business_title");
            getMobileActions().waitForSeconds(2);
            getMobileActions().flutterType(CATEGORIES_SEARCH_RESULTS, data.get(0));
            getMobileActions().waitForSeconds(2);
            getMobileActions().flutterClick("search_item_click_list_tile_" + data.get(1));
            int index;
            if (data.get(2).equalsIgnoreCase("Piece")) {
                index = 0;
                getMobileActions().flutterClick(String.format(PRODUCT_CART_ADD_BUTTON, 0, index));
            } else if (data.get(2).equalsIgnoreCase("Bag")) {
                index = 1;
                getMobileActions().flutterClick(String.format(PRODUCT_CART_ADD_BUTTON, 0, index));
            } else if (data.get(2).equalsIgnoreCase("Case")) {
                index = 2;
                getMobileActions().flutterClick(String.format(PRODUCT_CART_ADD_BUTTON, 0, index));
            } else {
                index = 0;
                getMobileActions().flutterClick(String.format(PRODUCT_CART_ADD_BUTTON, 0, index));
            }

            for (int j = 0; j < Integer.parseInt(data.get(3)); j++) {
                getMobileActions().flutterClick(String.format(PRODUCT_CART_INCREMENT_BUTTON, 0, index));
            }
            getMobileActions().flutterClick(BOTTOM_CART_ICON);
            if (list.size() - 1 != i) {
                getMobileActions().flutterClick(CartPage.ADD_MORE_ITEMS_BUTTON);
            }
        }
    }

    public static void clickOneRandomCategory() {
        int categoryNumber = AutomationUtils.generateRandomNoByRange(1, 4);
        getMobileActions().click(By.xpath("//android.view.View[@content-desc='Automation-Category-" + categoryNumber + "']/android.widget.ImageView"));
    }

    public static void searchItemDetails(String itemDetails) {
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::android.widget.Button"));
        searchItem(itemDetails);
        getUiActions().waitForSeconds(2);
        getMobileActions().flutterClick(CLICK_ITEM_INDEX_0);
    }

    public static void searchItem(String itemDetails) {
        getMobileActions().flutterWaitForVisibility(SEARCH_ITEM);
        getMobileActions().flutterClick(SEARCH_ITEM);
        getMobileActions().flutterWaitForVisibility("search_section_business_title");
        getMobileActions().waitForSeconds(2);
        getMobileActions().flutterType(ITEM_SEARCH_BOX, itemDetails);
    }

    public static void addRandomOneItemToCart() {
        int itemNo = AutomationUtils.generateRandomNoByRange(1, 38);
        String itemDetails = "Automation-Test-Item-Name-" + itemNo + "";
        searchItemDetails(itemDetails);
        getMobileActions().click(ADD_BUTTON_PIECE);
        ExpLayerCommonPage.saveCartItemQuantity(itemDetails, "1");
        String items_count = ExpLayerCommonPage.getCartItemQuantity("items");
        ExpLayerCommonPage.saveCartItemQuantity("item" + (Integer.parseInt(items_count) + 1), itemDetails);
        ExpLayerCommonPage.saveCartItemQuantity("items", String.valueOf(Integer.parseInt(items_count) + 1));
    }

    public static void addRandomCategoryItem() {
        clickOneRandomCategory();
        addRandomOneItemToCart();
    }
}

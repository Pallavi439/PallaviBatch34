package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoriesPage extends Step {

    static Logger log = LogManager.getLogger(CategoriesPage.class);

    public static By BOTTOM_CART_EMPTY_ICON = By.xpath("//android.view.View[@content-desc='Cart Tab 4 of 4']/android.widget.ImageView");
    public static By ADD_BUTTON_PIECE = By.xpath("(//android.widget.Button[@content-desc='Add'])[1]");

    public static String CATEGORIES_SEARCH = "header_search_key";
    public static String CATEGORIES_SEARCH_RESULTS = "search_er_input_box";
    public static String BOTTOM_CART_ICON = "header_cart_key";
    public static String PRODUCT_CART_ADD_BUTTON = "product_%s_add_button_%s";
    public static String PRODUCT_CART_INCREMENT_BUTTON = "product_%s_increment_button_%s";
    public static String SEARCH_ITEM = "header_search_key";
    public static String ITEM_SEARCH_BOX = "search_er_input_box";
    public static String CLICK_ITEM_INDEX = "search_item_click_list_tile_%s";
    public static String CATEGORY = "category_%s";


    public static void waitForVisibilityOfCatalogue() {
        getMobileActions().flutterWaitForVisibility(String.format(CATEGORY, 0));
    }

    public static void addItemToCart(List<List<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> data = AutomationUtils.replaceListData(list.get(i));
            getMobileActions().flutterWaitForVisibility(CATEGORIES_SEARCH);
            getMobileActions().flutterClick(CATEGORIES_SEARCH);
            try {
                getMobileActions().flutterWaitForVisibility("search_section_business_title");
                getMobileActions().flutterWaitForVisibility("search_item_click_list_tile_0");
                getMobileActions().waitForSeconds(2);
            } catch (Exception ignored) {
            }
            getMobileActions().waitForSeconds(2);
            getMobileActions().flutterType(CATEGORIES_SEARCH_RESULTS, data.get(0));
            getMobileActions().waitForSeconds(2);
            getMobileActions().flutterClick("search_item_click_list_tile_" + data.get(1));
            int index;
            getMobileActions().flutterWaitForVisibility(String.format(PRODUCT_CART_ADD_BUTTON, 0, 0));
            saveItemDetails();
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
                getMobileActions().waitForSeconds(1);
            }
        }
    }

    public static void clickOneRandomCategory() {
        int categoryNumber = AutomationUtils.generateRandomNoByRange(1, 4);
        getMobileActions().click(By.xpath("//android.view.View[@content-desc='Automation-Category-" + categoryNumber + "']/android.widget.ImageView"));
    }

    public static void searchItemDetails(String itemDetails) {
        searchItemDetails(itemDetails, "0");
    }

    public static void searchItemDetails(String itemDetails, String item_index) {
        getMobileActions().click(By.xpath("//*[contains(@content-desc,'Cart')]/preceding-sibling::android.widget.Button"));
        searchItem(itemDetails);
        getMobileActions().waitForSeconds(2);
        getMobileActions().flutterClick(String.format(CLICK_ITEM_INDEX, item_index));
    }

    public static void searchItem(String itemDetails) {
        getMobileActions().flutterWaitForVisibility(SEARCH_ITEM);
        getMobileActions().flutterClick(SEARCH_ITEM);
        try {
            getMobileActions().flutterClick("search_section_business_title");
        } catch (Exception ignored) {
        }
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

    public static void saveItemDetails() {
        try {
            getMobileActions().verifyContextAndSwitchToNativeContext();
            List<WebElement> s = getMobileActions().appiumDriver.findElements(By.xpath("//*[contains(@content-desc,'Automation-Test-Item')]"));
            String item_details = s.get(0).getAttribute("content-desc");
            String item_name = item_details.split("\n")[0];
            System.out.println("item name is " + item_name);
            AutomationUtils.getTestContext().put("item_name",item_name);

            String[] item_det = item_details.split("Rate");

            try {
                String[] piece_arr = item_det[1].split("\n");
                String piece = piece_arr[1].replace(" ", "").replace("₹", "");
                System.out.println("piece price is " + piece);
                AutomationUtils.getTestContext().put(item_name + "_piece", piece);
            } catch (Exception ignore) {
                System.out.println("piece price not found");
            }

            try {
                String bag = item_det[1].split("Bag")[1];
                bag = bag.split("\n")[1];
                bag = bag.replace(" ", "").replace("₹", "");
                System.out.println("price of bag is " + bag);
                AutomationUtils.getTestContext().put(item_name + "_bag", bag);
            } catch (Exception ignore) {
                System.out.println("bag price not found");
            }
            try {
                String case_detail = item_det[2].split("Case")[1];
                case_detail = case_detail.split("\n")[1];
                case_detail = case_detail.replace(" ", "").replace("₹", "");
                System.out.println("case price is " + case_detail);
                AutomationUtils.getTestContext().put(item_name + "_case", case_detail);
            } catch (Exception ignore) {
                System.out.println("case price not found");
            }

        } catch (Exception ignore) {

        }
    }
}

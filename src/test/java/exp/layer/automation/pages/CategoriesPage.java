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
    //flutter
    //search
    public static FlutterElement CATEGORIES_SEARCH = getFlutterActions().getFlutterFinder().byValueKey("header_search_key");
    public static FlutterElement CATEGORIES_SEARCH_RESULTS = getFlutterActions().getFlutterFinder().byValueKey("search_er_input_box");
    public static FlutterElement BOTTOM_CART_ICON = getFlutterActions().getFlutterFinder().byValueKey("header_cart_key");
    public static String PRODUCT_CART_ADD_BUTTON = "product_%s_add_button_%s";
    public static String PRODUCT_CART_INCREMENT_BUTTON = "product_%s_increment_button_%s";
    public static String PRODUCT_CART_DECREMENT_BUTTON = "product_%s_decrement_button_%s";

    //search item or category
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
                //default it will add piece
                index = 0;
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_ADD_BUTTON, 0, index)));
            }

            for (int j = 0; j < Integer.parseInt(data.get(3)); j++) {
                getFlutterActions().click(getFlutterActions().getFlutterFinder().byValueKey(String.format(PRODUCT_CART_INCREMENT_BUTTON, 0, index)));
            }
            getFlutterActions().click(BOTTOM_CART_ICON);
            // This will not run for the last item
            if (list.size() - 1 != i) {
                getFlutterActions().click(CartPage.ADD_MORE_ITEMS_BUTTON);
            }
        }
    }
}

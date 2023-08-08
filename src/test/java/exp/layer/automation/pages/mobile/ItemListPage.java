package exp.layer.automation.pages.mobile;

import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.List;

public class ItemListPage extends Step {

    public static final By VIEWS_ANDROID = AppiumBy.accessibilityId("Views");
    public static final By VIEWS_IOS = By.xpath("//*[@name='Views']");
    public static String OFFERS_ICON="offers_icon";
    public static String OFFERS_STRING="offer_text";
    public static String BRAND_ICON="brand_icon";
    public static String BRAND_NAME="Automation-Brand-1";
    public static String CATEGORY_ICON="category_icon";
    public static String ALL_ITEMS_ICON="sub_section_widget";
    public static String CART_ICON="header_cart_key";
    public static String SHOP_NOW_BUTTON="er_info_screen_primary_button";

    public static void increaseGivenItemQuantity(String item_name,String item_index, String item_type, String increase_amount) throws Exception {
        item_name = AutomationUtils.replaceTestDataVariable(item_name);
        item_index=AutomationUtils.replaceTestDataVariable(item_index);
        item_type = AutomationUtils.replaceTestDataVariable(item_type);
        increase_amount = AutomationUtils.replaceTestDataVariable(increase_amount);
        CategoriesPage.searchItemDetails(item_name,item_index);
        int i = Integer.parseInt(increase_amount);
        if (item_type.equalsIgnoreCase("Bag")) {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_increment_button_1");
            }
        } else if (item_type.equalsIgnoreCase("Case")) {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_increment_button_2");
            }
        } else {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_increment_button_0");
            }
        }
        CartPage.clickOnCartPageButton();
    }


    public static void increaseItemQuantity(List<List<String>> item_list) throws Exception {
            for (List<String> item_detail : item_list) {
                increaseGivenItemQuantity(item_detail.get(0), item_detail.get(1), item_detail.get(2),item_detail.get(3));
        }
    }

    public static void decreaseItemQuantity(List<List<String>> item_list) {

            for (List<String> item_detail : item_list) {
                decreaseGivenItemQuantity(item_detail.get(0), item_detail.get(1), item_detail.get(2),item_detail.get(3));
            }
    }

    public static void decreaseGivenItemQuantity(String item_name,String item_index, String item_type, String decrease_amount) {
        item_name = AutomationUtils.replaceTestDataVariable(item_name);
        item_index = AutomationUtils.replaceTestDataVariable(item_index);
        item_type = AutomationUtils.replaceTestDataVariable(item_type);
        decrease_amount = AutomationUtils.replaceTestDataVariable(decrease_amount);
        CategoriesPage.searchItemDetails(item_name,item_index);
        int i = Integer.parseInt(decrease_amount);
        if (item_type.equalsIgnoreCase("Bag")) {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_decrement_button_1");
            }
        } else if (item_type.equalsIgnoreCase("Case")) {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_decrement_button_2");
            }
        } else {
            for (int j = 0; j < i; j++) {
                getMobileActions().flutterClick("product_0_decrement_button_0");
            }
        }
        CartPage.clickOnCartPageButton();
    }

    public static void verifyBottomIcons(){
        getMobileActions().flutterWaitForVisibility(OFFERS_ICON);
        getMobileActions().flutterClick(OFFERS_ICON);
        getMobileActions().flutterWaitForVisibility(OFFERS_STRING);
        getMobileActions().flutterWaitForVisibility(BRAND_ICON);
        getMobileActions().flutterClick(BRAND_ICON);
        getMobileActions().flutterWaitForVisibility(BRAND_NAME);
        getMobileActions().flutterWaitForVisibility(CATEGORY_ICON);
        getMobileActions().flutterClick(CATEGORY_ICON);
        getMobileActions().flutterWaitForVisibility(ALL_ITEMS_ICON);
        getMobileActions().flutterWaitForVisibility(CART_ICON);
        getMobileActions().flutterClick(CART_ICON);
        getMobileActions().flutterWaitForVisibility(SHOP_NOW_BUTTON);


    }
}

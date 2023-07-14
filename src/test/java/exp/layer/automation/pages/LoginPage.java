package exp.layer.automation.pages;

import er.automation.engine.helpers.AutomationProperties;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class LoginPage extends Step {

    static Logger log = LogManager.getLogger(LoginPage.class);

    public static By DOHA_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Doha' or @content-desc='DOHA']");
    public static By PP_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Pre Prod' or @content-desc='PRE PROD']");

    public static By DEVICE_LOCATION = By.xpath("//*[@text='While using the app']");

    public static FlutterElement DEV_LOGIN_ICON = getFlutterActions().getFlutterFinder().byValueKey("dev_login_button");
    public static FlutterElement SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("dev_mobile_number_text_field");
    public static FlutterElement SALES_DEV_LOGIN_PASSWORD_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("dev_password_text_field");

    public static FlutterElement ENV_DROP_DOWN = getFlutterActions().getFlutterFinder().byValueKey("dev_env_change_dropdown");
    //public static FlutterElement DOHA_ENV_DROP_DOWN_LIST_VALUE = getFlutterActions().getFlutterFinder().byValueKey("Environment_Doha");
    //public static FlutterElement PP_ENV_DROP_DOWN_LIST_VALUE = getFlutterActions().getFlutterFinder().byValueKey("Environment_Pre Prod");
    public static FlutterElement LANGUAGE_NEXT_ARROW = getFlutterActions().getFlutterFinder().byValueKey("settings_locale_next_icon");
    public static FlutterElement SALES_DEV_LOGIN_CONTINUE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("open_dev_login_button");

    public static void salesAppLoginByUserDetails(String userName, String password) {
        try {
            getMobileActions().waitForVisibilityOfElementLocated(DashboardPage.BEAT_PLAN_BUTTON, 5);
        } catch (Exception e) {
            try {
                WebElement we = getMobileActions().waitForVisibilityOfElementLocated(DEVICE_LOCATION, 5);
                we.click();
                getFlutterActions().waitForVisibility(LANGUAGE_NEXT_ARROW);
                getFlutterActions().clickIfAvailable(LANGUAGE_NEXT_ARROW);
            } catch (Exception e1) {
                getFlutterActions().waitForVisibility(LANGUAGE_NEXT_ARROW);
                getFlutterActions().clickIfAvailable(LANGUAGE_NEXT_ARROW);
            }
            getFlutterActions().waitForVisibility(SALES_DEV_LOGIN_CONTINUE_BUTTON);
            for (int i = 0; i < 9; i++) {
                try {
                    SALES_DEV_LOGIN_CONTINUE_BUTTON.click();
                    //getFlutterActions().click(SALES_DEV_LOGIN_CONTINUE_BUTTON);
                } catch (Exception ignored) {
                }
            }
            getFlutterActions().click(SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX);
            getFlutterActions().type(SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX, userName);
            getFlutterActions().click(SALES_DEV_LOGIN_PASSWORD_TEXT_BOX);
            getFlutterActions().type(SALES_DEV_LOGIN_PASSWORD_TEXT_BOX, password);

            //code to select env
            getFlutterActions().click(ENV_DROP_DOWN);
            if (AutomationProperties.TEST_ENV.equalsIgnoreCase("doha")) {
                getMobileActions().click(DOHA_ENV_DROP_DOWN_LIST_VALUE);
            } else if (AutomationProperties.TEST_ENV.equalsIgnoreCase("preprod")) {
                getMobileActions().click(PP_ENV_DROP_DOWN_LIST_VALUE);
            }
            getFlutterActions().click(SALES_DEV_LOGIN_CONTINUE_BUTTON);
        }
    }
}
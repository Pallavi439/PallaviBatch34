package exp.layer.automation.pages;

import er.automation.engine.common.pages.ErApiPages;
import er.automation.engine.helpers.AutomationProperties;
import er.automation.engine.helpers.AutomationUtils;
import er.automation.engine.setup.Step;
import io.github.ashwith.flutter.FlutterElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class LoginPage extends Step {

    static Logger log = LogManager.getLogger(LoginPage.class);

    public static By DOHA_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Doha' or @content-desc='DOHA']");
    public static By PP_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Pre Prod' or @content-desc='PRE PROD']");

    public static By DEVICE_LOCATION = By.xpath("//*[@text='While using the app']");

    public static FlutterElement CAS_LOGIN_MOBILE_NO_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("mobile_no_text_field");
    public static FlutterElement CAS_LOGIN_CONTINUE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("mobile_no_submit_button");
    public static FlutterElement CAS_LOGIN_OTP_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("otp_text_field");
    public static FlutterElement CAS_LOGIN_VERIFY_AND_CONTINUE_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("otp_submit_button");
    public static FlutterElement CAS_LOGIN_RESEND_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("otp_submit_button");


    public static FlutterElement DEV_LOGIN_ER_LOGO = getFlutterActions().getFlutterFinder().byValueKey("open_dev_login_button");
    public static FlutterElement SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("dev_mobile_number_text_field");
    public static FlutterElement SALES_DEV_LOGIN_PASSWORD_TEXT_BOX = getFlutterActions().getFlutterFinder().byValueKey("dev_password_text_field");
    public static FlutterElement DEV_LOGIN_BUTTON = getFlutterActions().getFlutterFinder().byValueKey("dev_login_button");

    public static FlutterElement ENV_DROP_DOWN = getFlutterActions().getFlutterFinder().byValueKey("dev_env_change_dropdown");
    public static FlutterElement LANGUAGE_NEXT_ARROW = getFlutterActions().getFlutterFinder().byValueKey("settings_locale_next_icon");

    public static void caseLogin(List<String> list) throws IOException {
        String mobileNo = AutomationUtils.getTestData(list.get(0));
        try {
            WebElement we = getMobileActions().waitForVisibilityOfElementLocated(DEVICE_LOCATION, 3);
            we.click();
        } catch (Exception ignore) {
        }

        try {
            getFlutterActions().click(LANGUAGE_NEXT_ARROW);
        } catch (Exception ignore) {
        }

        getFlutterActions().waitForVisibility(DEV_LOGIN_ER_LOGO);
        getFlutterActions().click(CAS_LOGIN_MOBILE_NO_TEXT_BOX);
        getFlutterActions().type(CAS_LOGIN_MOBILE_NO_TEXT_BOX, mobileNo);
        getFlutterActions().click(CAS_LOGIN_CONTINUE_BUTTON);
        ErApiPages.getCasOtp(mobileNo);
        getMobileActions().type("${otp}", By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
        getMobileActions().waitForSeconds(5);
    }


    public static void salesAppLoginByUserDetails(String userName, String password) {
        try {
            WebElement we = getMobileActions().waitForVisibilityOfElementLocated(DEVICE_LOCATION, 3);
            we.click();
        } catch (Exception ignore) {

        }
        try {
            getFlutterActions().click(LANGUAGE_NEXT_ARROW);
        } catch (Exception ignore) {

        }
        getFlutterActions().waitForVisibility(DEV_LOGIN_ER_LOGO);
        for (int i = 0; i < 9; i++) {
            try {
                DEV_LOGIN_ER_LOGO.click();
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
        getFlutterActions().click(DEV_LOGIN_BUTTON);
    }
}

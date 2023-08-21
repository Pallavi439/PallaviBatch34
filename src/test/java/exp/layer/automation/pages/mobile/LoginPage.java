package exp.layer.automation.pages.mobile;

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

    public static String CAS_LOGIN_MOBILE_NO_TEXT_BOX = "mobile_no_text_field";
    public static String CAS_LOGIN_CONTINUE_BUTTON = "mobile_no_submit_button";
    public static String CAS_LOGIN_VERIFY_AND_CONTINUE_BUTTON = "otp_submit_button";
    public static String CAS_LOGIN_RESEND_BUTTON = "";
    public static String DEV_LOGIN_ER_LOGO = "open_dev_login_button";
    public static String SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX = "dev_mobile_number_text_field";
    public static String SALES_DEV_LOGIN_PASSWORD_TEXT_BOX = "dev_password_text_field";
    public static String DEV_LOGIN_BUTTON = "dev_login_button";
    public static String ENV_DROP_DOWN = "dev_env_change_dropdown";
    public static String LANGUAGE_NEXT_ARROW = "settings_locale_next_icon";
    public static String BANNER_CLOSE_BUTTON="startup_banner_close_icon";

    public static By DOHA_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Doha' or @content-desc='DOHA']");
    public static By PP_ENV_DROP_DOWN_LIST_VALUE = By.xpath("//*[@content-desc='Pre Prod' or @content-desc='PRE PROD']");
    public static By DEVICE_LOCATION = By.xpath("//*[@text='While using the app']");

    public static void caseLogin(List<String> list) throws IOException {
        String mobileNo = AutomationUtils.getTestData(list.get(0));
        try {
            WebElement we = getMobileActions().waitForVisibilityOfElementLocated(DEVICE_LOCATION, 3);
            we.click();
        } catch (Exception ignore) {
        }
        try {
            getMobileActions().flutterClick(LANGUAGE_NEXT_ARROW);
        } catch (Exception ignore) {
        }
        getMobileActions().flutterWaitForVisibility(DEV_LOGIN_ER_LOGO);
        getMobileActions().flutterClick(CAS_LOGIN_MOBILE_NO_TEXT_BOX);
        getMobileActions().flutterType(CAS_LOGIN_MOBILE_NO_TEXT_BOX, mobileNo);
        getMobileActions().flutterClick(CAS_LOGIN_CONTINUE_BUTTON);
        getMobileActions().waitForSeconds(10);
        ErApiPages.getCasOtp(mobileNo);
        getMobileActions().waitForSeconds(5);
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
            getMobileActions().flutterClick(LANGUAGE_NEXT_ARROW);
        } catch (Exception ignore) {
        }
        getMobileActions().flutterWaitForVisibility(DEV_LOGIN_ER_LOGO);
        for (int i = 0; i < 9; i++) {
            try {
                FlutterElement fe = getMobileActions().getFeByKey(DEV_LOGIN_ER_LOGO);
                fe.click();
            } catch (Exception ignored) {
            }
        }
        getMobileActions().flutterClick(SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX);
        getMobileActions().flutterType(SALES_DEV_LOGIN_MOBILE_NO_TEXT_BOX, userName);
        getMobileActions().flutterClick(SALES_DEV_LOGIN_PASSWORD_TEXT_BOX);
        getMobileActions().flutterType(SALES_DEV_LOGIN_PASSWORD_TEXT_BOX, password);

        //code to select env
        getMobileActions().flutterClick(ENV_DROP_DOWN);
        if (AutomationProperties.TEST_ENV.equalsIgnoreCase("doha")) {
            getMobileActions().click(DOHA_ENV_DROP_DOWN_LIST_VALUE);
        } else if (AutomationProperties.TEST_ENV.equalsIgnoreCase("preprod")) {
            getMobileActions().click(PP_ENV_DROP_DOWN_LIST_VALUE);
        }
        getMobileActions().flutterClick(DEV_LOGIN_BUTTON);
        try {
            getMobileActions().flutterWaitForVisibility(BANNER_CLOSE_BUTTON);
            getMobileActions().flutterClick(BANNER_CLOSE_BUTTON);
        }
        catch (Exception ignored){}
    }
}

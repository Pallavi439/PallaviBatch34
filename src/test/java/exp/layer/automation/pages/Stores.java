package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

public class Stores extends Step {



    public static String STORE_NAME = "//*[contains(@content-desc,'%s')]";

    public static final By INFORMED_CUSTOMER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Informed Customer']");

    //Capture Store Image
    //public static final By ADD_STORE_IMAGE_LINK = By.xpath("//android.view.View[@content-desc='Add Store Image']/android.widget.ImageView[2]");

    public static final By ADD_STORE_IMAGE_LINK = By.xpath("//android.view.View[@content-desc='Add Store Image']");
    public static final By STORE_IMAGE_SUBMIT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Submit']");


    public static final By REMOTE_ORDER_BUTTON = By.xpath("//android.widget.Button[@content-desc='Take a Remote Order']");


    public static void clickOnStore(String storeName) throws Exception {
        getMobileActions().click(By.xpath(String.format(STORE_NAME,storeName)));
        //getMobileActions().click(By.xpath("//android.view.View[@content-desc='" + storeName + "']"));
        getMobileActions().clickIfAvailable(INFORMED_CUSTOMER_BUTTON);
    }

    public static void captureStoreImage() throws Exception {
        getMobileActions().click(ADD_STORE_IMAGE_LINK);
        KeyEvent event = new KeyEvent(AndroidKey.CAMERA);
        AndroidDriver driver = (AndroidDriver) getMobileActions().appiumDriver;
        driver.pressKey(event);
        //getAndroidActions().click(By.xpath(".//android.widget.TextView[@text='OK']"));
        getMobileActions().click(By.xpath("//android.widget.ImageButton[@content-desc='Done']"));
        getMobileActions().click(STORE_IMAGE_SUBMIT_BUTTON);
    }

    public static void clickOnRemoteOrderButton() throws Exception {
        getMobileActions().click(REMOTE_ORDER_BUTTON);
    }
}

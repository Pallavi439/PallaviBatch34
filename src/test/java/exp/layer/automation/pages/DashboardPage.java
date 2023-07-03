package exp.layer.automation.pages;

import er.automation.engine.setup.Step;
import org.openqa.selenium.By;

public class DashboardPage extends Step {

    public static By BEAT_PLAN_BUTTON =  By.xpath("//*[@content-desc='Start Beat' or @content-desc='Continue' or @content-desc='Start Day']");

    public static void clickOnBeatButton() throws Exception {
        getMobileActions().click(BEAT_PLAN_BUTTON);
    }
}

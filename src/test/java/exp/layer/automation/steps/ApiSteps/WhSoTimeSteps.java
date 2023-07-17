package exp.layer.automation.steps.ApiSteps;
import er.automation.engine.common.pages.ErApiPages;
import exp.layer.automation.pages.*;
import exp.layer.automation.pages.ApiPages.WhSoTimePages;
import io.cucumber.java.en.And;

import java.io.IOException;
import java.util.List;

public class WhSoTimeSteps {
    @And("{word} hit get_beat api and get time")
    public void getBeatApi(String word, List<String> list) throws IOException {
        WhSoTimePages.getBeatApi(list);
    }
}

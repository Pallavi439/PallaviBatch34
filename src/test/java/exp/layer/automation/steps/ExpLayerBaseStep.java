package exp.layer.automation.steps;

import er.automation.engine.helpers.AppCentreUtils;
import io.cucumber.java.Before;

import java.io.IOException;

public class ExpLayerBaseStep {

    //@Before
    public void expLayerBeforeAllScenario() throws IOException {

         AppCentreUtils.downloadReleasedMobileApp();

       // System.out.println(">>>>>>>>>"+id);


    }
}

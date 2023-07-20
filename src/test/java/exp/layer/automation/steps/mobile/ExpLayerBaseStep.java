package exp.layer.automation.steps.mobile;

import er.automation.engine.helpers.AppCentreUtils;

import java.io.IOException;

public class ExpLayerBaseStep {

    //@Before
    public void expLayerBeforeAllScenario() throws IOException {

         AppCentreUtils.downloadReleasedMobileApp();

       // System.out.println(">>>>>>>>>"+id);


    }
}

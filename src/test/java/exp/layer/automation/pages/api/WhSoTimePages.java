package exp.layer.automation.pages.api;

import er.automation.engine.common.pages.CommonApiMethod;
import er.automation.engine.common.pages.CommonApiPage;
import er.automation.engine.common.pages.WRunCommonApiPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class WhSoTimePages {

    public static String GET_BEAT_API = "/api/method/withrun_erpnext.withrun_erpnext.doctype.beat_plan.beat_plan.get_beat";

    public static void getBeatApi(List<String> list) throws IOException {
        WRunCommonApiPage.salesPersonLogin(list.get(0), list.get(1));
        HashMap<String, String> paramMap = new HashMap();
        HashMap<String, String> formParamMap = new HashMap();
        HashMap<String, String> headerMap = new HashMap();
        headerMap.put("Accept", "application/json");
        headerMap.put("Cookie", "${sales-app-cookie}");
        CommonApiMethod.getPostCall("api-base-url", GET_BEAT_API, paramMap, formParamMap, headerMap);
        CommonApiPage.verifyStatusCode(200);
    }
}

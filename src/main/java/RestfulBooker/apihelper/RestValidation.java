package RestfulBooker.apihelper;

import ExtentReport.ReportLogger;
import io.restassured.response.Response;
import org.testng.Assert;

public class RestValidation {
    public static void validateStatusCode(Response response, int statusCode) {
        int statusCodeResponse = response.getStatusCode();
        Assert.assertEquals(statusCodeResponse, statusCode);
    }

    public static void validateField(Object expectedValue, Object actualValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }

}

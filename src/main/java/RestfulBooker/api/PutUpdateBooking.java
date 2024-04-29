package RestfulBooker.api;

import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.utilities.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.tools.ant.types.selectors.ReadableSelector;

import java.util.List;

public class PutUpdateBooking {
    public Response putUpdateBooking(Object payload, String extendedUrl, List<Header> headersList)
    {
        RestAssured.baseURI= ConfigReader.getProperty("BaseURI");
        return RestUtils.putResponse(payload,extendedUrl,headersList);
    }
}

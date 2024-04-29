package RestfulBooker.api;

import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.utilities.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.List;

public class PatchPartialUpdateBooking {
    public Response patchPartialUpdateBooking(Object payload, String extendedUrl, List<Header> headersList)
    {
        RestAssured.baseURI= ConfigReader.getProperty("BaseURI");
        return RestUtils.patchResponse(payload,extendedUrl,headersList);
    }
}

package RestfulBooker.api;

import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.utilities.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.List;

public class GetBookingIds {
    public Response getBookingIds(String extentedUrl, List<Header> headerList)
    {
        RestAssured.baseURI= ConfigReader.getProperty("BaseURI");
        return RestUtils.getResponse(extentedUrl, headerList);
    }
}

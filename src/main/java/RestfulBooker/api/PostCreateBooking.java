package RestfulBooker.api;

import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.utilities.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;


public class PostCreateBooking {
    public Response postCreateBooking(Object payload, String extentedUrl, List<Header> headerList)
    {
        RestAssured.baseURI= ConfigReader.getProperty("BaseURI");
        return RestUtils.postResponse(payload, extentedUrl, headerList);
    }
}

package RestfulBooker.api;

import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.utilities.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.List;

public class PostAutorization {
    public Response postAuthorization(Object payload, String extendenUrl, List<Header> headerList)
    {
        RestAssured.baseURI= ConfigReader.getProperty("BaseURI");
        return RestUtils.postResponse(payload,extendenUrl,headerList);
    }
}

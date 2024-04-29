package RestfulBooker.apihelper;

import ExtentReport.ReportLogger;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Headers;
import java.util.List;
import static io.restassured.RestAssured.given;

public class RestUtils {

    public static Response postResponse(Object payload, String extendedURL, List<Header> headers) {
        ReportLogger.info("HTTP URL : "+extendedURL);
        Headers requestHeader = new Headers(headers);
        ReportLogger.info("HEADERS : "+headers);
        String request = RestUtils.ConvertToJson(payload);
        ReportLogger.info("REQUEST BODY : "+request);
        Response response = given().relaxedHTTPSValidation()
                .headers(requestHeader)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(extendedURL);
        ReportLogger.info("RESPONSE BODY : "+response.asString());
        response.then().log().all();
        return response;
    }
    public static Response getResponse(String extendedURL, List<Header> headers) {
        ReportLogger.info("HTTP URL : "+extendedURL);
        Headers requestHeader = new Headers(headers);
        ReportLogger.info("HEADERS : "+headers);
        Response response = given().relaxedHTTPSValidation()
                .headers(requestHeader)
                .get(extendedURL);
        ReportLogger.info("RESPONSE BODY : "+response.asString());
        response.then().log().all();
        return response;
    }

    public static  Response putResponse(Object payload,String extendedURL, List<Header> headers)
    {
        ReportLogger.info("HTTP URL : "+extendedURL);
        Headers requestHeaders=new Headers(headers);
        ReportLogger.info("HEADERS : "+headers);
        String request = RestUtils.ConvertToJson(payload);
        ReportLogger.info("REQUEST BODY : "+request);
        Response response =given().relaxedHTTPSValidation()
                .headers(requestHeaders)
                .body(payload)
                .put(extendedURL);
        response.then().log().all();
        return response;
    }
    public static String ConvertToJson(Object payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        String finalPayload = null;
        try {
            finalPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return finalPayload;
    }

    public static  Response patchResponse(Object payload,String extendedURL, List<Header> headers)
    {
        ReportLogger.info("HTTP URL : "+extendedURL);
        Headers requestHeaders=new Headers(headers);
        ReportLogger.info("HEADERS : "+headers);
        String request = RestUtils.ConvertToJson(payload);
        ReportLogger.info("REQUEST BODY : "+request);
        Response response =given().relaxedHTTPSValidation()
                .headers(requestHeaders)
                .body(payload)
                .patch(extendedURL);
        response.then().log().all();
        return response;
    }
    public static Response deleteResponse(String extendedURL,List<Header> headers) {
        ReportLogger.info("HTTP URL : "+extendedURL);
        Headers requestHeader = new Headers(headers);
        ReportLogger.info("HEADERS : "+headers);
        Response response = given().relaxedHTTPSValidation()
                .headers(requestHeader)
                .contentType(ContentType.JSON)
                .delete(extendedURL);
        ReportLogger.info("RESPONSE BODY : "+response.asString());
        response.then().log().all();
        return response;
    }
}

package restfull.booking;

import RestfulBooker.api.GetHealthCheck;
import RestfulBooker.api.PostCreateBooking;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestUtils;
import RestfulBooker.apihelper.RestValidation;
import RestfulBooker.base.TestBase;
import RestfulBooker.payload.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import request.createBooking.CreateBooking;

public class PostCreateBookingTest extends TestBase {
    String extenedUrl;
    PostCreateBooking postCreateBookingrequest;
    CreateBookingRequest createBookingRequestPayload;
    CreateBooking payload;


    @BeforeClass
    public void initialize(){

        data.firstname="sounak";
        data.lastname="roy";
        data.additionalneeds="Breakfast";
        data.checkout="2023-09-05";
        data.checkin="2023-09-10";
        data.totalprice=111;
        data.depositpaid=true;

        postCreateBookingrequest = new PostCreateBooking();
        createBookingRequestPayload = new CreateBookingRequest(data);
        extenedUrl= Resource.postCreateBooking();
        payload=createBookingRequestPayload.createBookingPayload();



    }
    @BeforeMethod
    public void beforeTest() {

        headers = Headers.getHeader();


    }

    @Test
    public void PostCreateBookingTest01_WhenValidRequst_ShouldReturn200()
    {
        Response response=postCreateBookingrequest.postCreateBooking(payload, extenedUrl,headers);
        RestValidation.validateStatusCode(response,200);
    }
    @Test
    public void PostCreateBookingTest02_WhenValidRequst_ValidateResponseValue()
    {
        Response response=postCreateBookingrequest.postCreateBooking(payload, extenedUrl,headers);
        RestValidation.validateField(response.jsonPath().getString("booking.firstname"),data.firstname);
        RestValidation.validateField(response.jsonPath().getString("booking.lastname"),data.lastname);
        RestValidation.validateField(response.jsonPath().getInt("booking.totalprice"),data.totalprice);
        RestValidation.validateField(response.jsonPath().getString("booking.bookingdates.checkin"),data.checkin);
        RestValidation.validateField(response.jsonPath().getString("booking.bookingdates.checkout"),data.checkout);
    }
}

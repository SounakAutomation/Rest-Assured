package restfull.booking;

import RestfulBooker.api.GetBookingId;
import RestfulBooker.api.PostCreateBooking;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestValidation;
import RestfulBooker.base.TestBase;
import RestfulBooker.payload.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import request.createBooking.CreateBooking;

public class GetBookingIdTest extends TestBase {
    String extenedUrl;
    PostCreateBooking postCreateBookingrequest;
    CreateBookingRequest createBookingRequestPayload;
    CreateBooking payload;
    Response postResponse;
    GetBookingId getBookingId;
    String getExtenedUrl;

    @BeforeTest
    public void initialize(){
        postCreateBookingrequest = new PostCreateBooking();
        createBookingRequestPayload = new CreateBookingRequest(data);
        extenedUrl= Resource.postCreateBooking();
        payload=createBookingRequestPayload.createBookingPayload();
        getBookingId=new GetBookingId();

    }
    @BeforeMethod
    public void beforeTest() {
        headers = Headers.getHeader();
    }

    @Test
    public void GetBookingIdTest01_WhenValidRequst_ShouldReturn200()
    {
        postResponse=postCreateBookingrequest.postCreateBooking(payload, extenedUrl,headers);
        data.id=postResponse.jsonPath().getInt("bookingid");
        getExtenedUrl=Resource.getBookingId(data.id);
        Response response=getBookingId.getBookingId(getExtenedUrl,headers);
        RestValidation.validateStatusCode(response,200);
    }
    @Test
    public void GetBookingIdTest02_WhenValidRequst_ValidateField()
    {
        postResponse=postCreateBookingrequest.postCreateBooking(payload, extenedUrl,headers);
        data.id=postResponse.jsonPath().getInt("bookingid");
        getExtenedUrl=Resource.getBookingId(data.id);
        Response response=getBookingId.getBookingId(getExtenedUrl,headers);
        RestValidation.validateField(response.jsonPath().getString("firstname"),data.firstname);
        RestValidation.validateField(response.jsonPath().getString("lastname"),data.lastname);
    }
    @Test
    public void GetBookingIdTest03_WhenInvalidRequst_shouldreturn404()
    {
        getExtenedUrl=Resource.getBookingId(-9776);
        Response response=getBookingId.getBookingId(getExtenedUrl,headers);
        RestValidation.validateStatusCode(response,404);
    }
}

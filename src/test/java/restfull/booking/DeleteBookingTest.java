package restfull.booking;

import RestfulBooker.api.DeleteBooking;
import RestfulBooker.api.GetBookingId;
import RestfulBooker.api.PostAutorization;
import RestfulBooker.api.PostCreateBooking;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestValidation;
import RestfulBooker.base.TestBase;
import RestfulBooker.payload.CreateBookingRequest;
import RestfulBooker.payload.PostAuthorizationRequest;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import request.autorization.AutorizatonPayload;
import request.createBooking.CreateBooking;

public class DeleteBookingTest extends TestBase {
    String postExtendUrl;
    PostCreateBooking postCreateBookingrequest;
    CreateBookingRequest createBookingRequestPayload;
    CreateBooking payload;
    Response postResponse;
    DeleteBooking deleteBooking;
    String extenedUrl;
    PostAutorization postAutorization;
    PostAuthorizationRequest postAuthorizationRequest;
    AutorizatonPayload authPayload;
    String postAuthExtendedUrl;
    Response postAuthResponse;

    @BeforeClass
    public void initialize(){
        postCreateBookingrequest = new PostCreateBooking();
        createBookingRequestPayload = new CreateBookingRequest(data);
        postExtendUrl= Resource.postCreateBooking();
        payload=createBookingRequestPayload.createBookingPayload();
        deleteBooking=new DeleteBooking();
        postAuthorizationRequest=new PostAuthorizationRequest(data);
        postAutorization =new PostAutorization();
        authPayload=postAuthorizationRequest.posAutorizationRequest();
        postAuthExtendedUrl=Resource.postAutorization();


    }
    @BeforeMethod
    public void beforeTest() {

        headers = Headers.getHeader();
        postAuthResponse=postAutorization.postAuthorization(authPayload,postAuthExtendedUrl,headers);
        data.token=postAuthResponse.jsonPath().getString("token");
        headers.add(new Header("Cookie", "token="+data.token));

    }

    @Test
    public void DeleteBookingTest01_WhenValidRequst_ShouldReturn200()
    {
        postResponse=postCreateBookingrequest.postCreateBooking(payload, postExtendUrl,headers);
        data.id=postResponse.jsonPath().getInt("bookingid");

        extenedUrl=Resource.getBookingId(data.id);
        Response response=deleteBooking.deleteBooking(extenedUrl,headers);
        RestValidation.validateStatusCode(response,201);
    }
    @Test
    public void DeleteBookingTest02_WhenInvalidRequst_ShouldReturn400()
    {
        extenedUrl=Resource.getBookingId(-86);
        Response response=deleteBooking.deleteBooking(extenedUrl,headers);
        RestValidation.validateStatusCode(response,405);
    }
}

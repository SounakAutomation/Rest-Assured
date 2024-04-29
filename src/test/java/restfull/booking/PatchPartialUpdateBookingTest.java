package restfull.booking;

import RestfulBooker.api.PatchPartialUpdateBooking;
import RestfulBooker.api.PostAutorization;
import RestfulBooker.api.PostCreateBooking;
import RestfulBooker.api.PutUpdateBooking;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestUtils;
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

public class PatchPartialUpdateBookingTest extends TestBase {
    String extenedUrl;
    PatchPartialUpdateBooking patchPartialUpdateBooking;
    CreateBookingRequest createBookingRequestPayload;
    PostCreateBooking postCreateBookingrequest;
    CreateBooking payload;
    Response postResponse;
    String postExtenedUrl;
    AutorizatonPayload authPayload;
    PostAuthorizationRequest postAuthorizationRequest;
    PostAutorization postAutorization;
    Response postAuthResponse;
    String postAuthExtendedUrl;

    @BeforeClass
    public void initialize(){
        headers = Headers.getHeader();
        postAutorization=new PostAutorization();
        postAuthorizationRequest=new PostAuthorizationRequest(data);
        postCreateBookingrequest = new PostCreateBooking();
        patchPartialUpdateBooking = new PatchPartialUpdateBooking();
        createBookingRequestPayload = new CreateBookingRequest(data);
        postExtenedUrl= Resource.postCreateBooking();
        payload=createBookingRequestPayload.createBookingPayload();
        authPayload=postAuthorizationRequest.posAutorizationRequest();
        postAuthExtendedUrl=Resource.postAutorization();
    }

    @BeforeMethod
    public void beforeTest() {
        postResponse=postCreateBookingrequest.postCreateBooking(payload, postExtenedUrl,headers);
        data.id=postResponse.jsonPath().getInt("bookingid");
        extenedUrl=Resource.putBooking(data.id);
        data.firstname="gourav";
        data.lastname="roy";
        data.additionalneeds=null;
        data.checkout="2023-10-20";
        data.checkin="2023-10-10";
        data.totalprice=null;
        data.depositpaid=null;
        postAuthResponse=postAutorization.postAuthorization(authPayload,postAuthExtendedUrl,headers);
        data.token=postAuthResponse.jsonPath().getString("token");
    }

    @Test
    public void PatchPartialUpdateBookingTest01_WhenValidRequst_ShouldReturn200()
    {
        createBookingRequestPayload = new CreateBookingRequest(data);
        payload=createBookingRequestPayload.createBookingPayload();
        System.out.println(RestUtils.ConvertToJson(payload));
        headers.add(new Header("Cookie", "token="+data.token));
        Response response=patchPartialUpdateBooking.patchPartialUpdateBooking(payload, extenedUrl,headers);
        RestValidation.validateStatusCode(response,200);
    }
}

package restfull.booking;

import RestfulBooker.api.GetBookingId;
import RestfulBooker.api.GetBookingIds;
import RestfulBooker.api.PostCreateBooking;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestValidation;
import RestfulBooker.base.TestBase;
import RestfulBooker.payload.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import request.createBooking.CreateBooking;

public class GetBookingIdsTest extends TestBase {
    String extenedUrl;
    GetBookingIds getBookingIds;

    @BeforeClass
    public void initialize(){
        extenedUrl= Resource.postCreateBooking();
        getBookingIds=new GetBookingIds();
    }
    @BeforeMethod
    public void beforeTest() {

        headers = Headers.getHeader();
    }

    @Test
    public void GetBookingIdsTest01_WhenValidRequst_ShouldReturn200()
    {
        extenedUrl=Resource.getBookingIds();
        Response response=getBookingIds.getBookingIds(extenedUrl,headers);
        RestValidation.validateStatusCode(response,200);
    }
}

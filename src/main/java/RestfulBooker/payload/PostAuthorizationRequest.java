package RestfulBooker.payload;

import RestfulBooker.datafactory.DataFactory;
import request.autorization.AutorizatonPayload;
import request.createBooking.Bookingdates;
import request.createBooking.CreateBooking;

public class PostAuthorizationRequest {
    DataFactory data;
    public PostAuthorizationRequest(DataFactory data) {
        this.data=data;
    }
    public AutorizatonPayload posAutorizationRequest()
    {
        AutorizatonPayload payload=new AutorizatonPayload();
        payload.setUsername(data.username);
        payload.setPassword(data.password);
        return payload;
    }
}

package RestfulBooker.payload;

import RestfulBooker.datafactory.DataFactory;
import request.createBooking.Bookingdates;
import request.createBooking.CreateBooking;


public class CreateBookingRequest {
    DataFactory data;
    public CreateBookingRequest(DataFactory data) {
        this.data=data;
    }

    public CreateBooking createBookingPayload()
    {
        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin(data.checkin);
        bookingdates.setCheckout(data.checkout);
        CreateBooking payload =new CreateBooking();
        payload.setAdditionalneeds(data.additionalneeds);
        payload.setDepositpaid(data.depositpaid);
        payload.setFirstname(data.firstname);
        payload.setLastname(data.lastname);
        payload.setTotalprice(data.totalprice);
        payload.setBookingdates(bookingdates);
        return payload;
    }
}

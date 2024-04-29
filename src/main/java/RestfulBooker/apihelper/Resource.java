package RestfulBooker.apihelper;

import RestfulBooker.utilities.ConfigReader;

public class Resource {
    public static String postCreateBooking() {return "/booking";}
    public static String postAutorization() {return "/auth";}
    public static String deleteBooking() {return "/booking/";}


    public static String getBookingIds() {return "/booking";}

    public static String getHealth() {return "/ping";}

    public static String getBookingId(int id) {return String.format("/booking/%s",id);}
    public static String putBooking(int id) {return String.format("/booking/%s",id);}


}

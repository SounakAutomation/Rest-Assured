package RestfulBooker.apihelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import io.restassured.http.Header;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Headers {
    public static List<Header> getHeader() {

        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content-Type", "application/json"));
//        headerList.add(new Header("ApplicationId", "SC"));
//        headerList.add(new Header("CorrelationId", UUID.randomUUID().toString()));
//        //email of the following user should not be null as it is being validated in the test cases
//        headerList.add(new Header("UserId", "690"));

        return headerList;
    }
}

package webservice.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDatesResource {

    private String checkIn;
    private String checkOut;

    public String getCheckIn() {
        return checkIn;
    }

    @JsonProperty("checkin")
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    @JsonProperty("checkout")
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "BookingDatesResource [checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
    }

}

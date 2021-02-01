package webservice.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingResource {

    private Integer bookingId;
    private BookingResource bookingResource;

    public Integer getBookingId() {
        return bookingId;
    }

    @JsonProperty("bookingid")
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public BookingResource getBookingResource() {
        return bookingResource;
    }

    @JsonProperty("booking")
    public void setBookingResource(BookingResource bookingResource) {
        this.bookingResource = bookingResource;
    }

    @Override
    public String toString() {
        return "CreateBookingResource [bookingId=" + bookingId + ", bookingResource=" + bookingResource + "]";
    }
}

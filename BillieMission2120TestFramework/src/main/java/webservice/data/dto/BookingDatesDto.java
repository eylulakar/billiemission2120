package webservice.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDatesDto {

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
        return "BookingDatesDto [checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
    }

    public static BookingDatesDto createBookingDatesDto(final String checkIn, final String checkOut) {
        BookingDatesDto bookingDatesDto = new BookingDatesDto();
        bookingDatesDto.setCheckIn(checkIn);
        bookingDatesDto.setCheckOut(checkOut);
        return bookingDatesDto;
    }
}

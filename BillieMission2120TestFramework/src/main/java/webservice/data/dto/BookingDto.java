package webservice.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.BooleanNode;

public class BookingDto {

    private String firstName;
    private String lastName;
    private Integer totalPrice;
    private Boolean depositPaid;
    private BookingDatesDto bookingDates;
    private String additionalNeeds;

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstname")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastname")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("totalprice")
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getDepositPaid() {
        return depositPaid;
    }

    @JsonProperty("depositpaid")
    public void setDepositPaid(Boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public BookingDatesDto getBookingDates() {
        return bookingDates;
    }

    @JsonProperty("bookingdates")
    public void setBookingDates(BookingDatesDto bookingDates) {
        this.bookingDates = bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    @JsonProperty("additionalneeds")
    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }

    @Override
    public String toString() {
        return "BookingDto [firstName=" + firstName + ", lastName=" + lastName + ", totalPrice=" + totalPrice
                + ", depositPaid=" + depositPaid + ", bookingDates=" + bookingDates + ", additionalNeeds=" + additionalNeeds + "]";
    }

    public static BookingDto createBookingDto(final String firstName, final String lastName, final Integer totalPrice, final Boolean depositPaid,
                                              final String additionalNeeds, final BookingDatesDto bookingDatesDto) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setFirstName(firstName);
        bookingDto.setLastName(lastName);
        bookingDto.setTotalPrice(totalPrice);
        bookingDto.setDepositPaid(depositPaid);
        bookingDto.setAdditionalNeeds(additionalNeeds);
        bookingDto.setBookingDates(bookingDatesDto);
        return bookingDto;
    }

}

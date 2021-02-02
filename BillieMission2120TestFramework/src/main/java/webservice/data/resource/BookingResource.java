package webservice.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResource {

    private String firstName;
    private String lastName;
    private Integer totalPrice;
    private Boolean depositPaid;
    private BookingDatesResource bookingDates;
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

    public BookingDatesResource getBookingDates() {
        return bookingDates;
    }

    @JsonProperty("bookingdates")
    public void setBookingDates(BookingDatesResource bookingDates) {
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
        return "BookingResource [firstName=" + firstName + ", lastName=" + lastName + ", totalPrice=" + totalPrice
                + ", depositPaid=" + depositPaid + ", bookingDates=" + bookingDates + ", additionalNeeds=" + additionalNeeds + "]";
    }


}

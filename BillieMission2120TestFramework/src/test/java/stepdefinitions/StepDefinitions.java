package stepdefinitions;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ReadPropertyFromConfig;
import webservice.data.dto.BookingDatesDto;
import webservice.data.dto.BookingDto;
import webservice.data.resource.BookingResource;
import webservice.data.resource.CreateBookingResource;
import webservice.serviceclient.BookingServiceClient;
import webservice.serviceclient.BookingServiceResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScoped
public class StepDefinitions {
    private final BookingServiceClient serviceClient = new BookingServiceClient();
    private static BookingServiceResponse<CreateBookingResource> createBookingServiceResponse;
    private static BookingServiceResponse<BookingResource> updateBookingServiceResponse;
    private static BookingServiceResponse<BookingResource> getBookingServiceResponse;
    private static BookingServiceResponse<?> deleteBookingServiceResponse;
    private static BookingDto createBookingDto;
    private static BookingDto updateBookingDto;
    private static BookingResource bookingResource;
    private static int bookingId;
    private static int responseCode;
    private static String authToken;

    @Given("I set create booking request body json")
    public void prepareCreateBookingObject() throws Exception {
        BookingDatesDto bookingDatesDto = BookingDatesDto.createBookingDatesDto("2015-07-18", "2015-07-20");
        createBookingDto = BookingDto.createBookingDto("testFirstName", "testLastName", 50, true, "Sports", bookingDatesDto);
    }

    @When("I send create booking http request")
    public void sendCreateBookingRequest() throws Exception {
        createBookingServiceResponse = serviceClient.createBooking(createBookingDto);
        responseCode = createBookingServiceResponse.getStatusCode();
    }

    @Then("Response status should be {int}")
    public void checkResponseStatus(int statusCode) {
        assertThat(responseCode).isEqualTo(statusCode);
    }

    @And("Response body object booking matches create booking request object")
    public void checkCreateBookingResponseBookingObject() {
        BookingResource bookingResource = createBookingServiceResponse.getResult().getBookingResource();

        assertThat(bookingResource.getFirstName()).isEqualTo(createBookingDto.getFirstName());
        assertThat(bookingResource.getLastName()).isEqualTo(createBookingDto.getLastName());
        assertThat(bookingResource.getAdditionalNeeds()).isEqualTo(createBookingDto.getAdditionalNeeds());
        assertThat(bookingResource.getTotalPrice()).isEqualTo(createBookingDto.getTotalPrice());
        assertThat(bookingResource.getDepositPaid()).isEqualTo(createBookingDto.getDepositPaid());
        assertThat(bookingResource.getBookingDates()).isNotNull();
        assertThat(bookingResource.getBookingDates().getCheckIn()).isEqualTo(createBookingDto.getBookingDates().getCheckIn());
        assertThat(bookingResource.getBookingDates().getCheckOut()).isEqualTo(createBookingDto.getBookingDates().getCheckOut());
    }

    @And("Response body object has non-null id value")
    public void checkCreateBookingResponseId() {
        assertThat(createBookingServiceResponse.getResult().getBookingId()).isNotNull();
        bookingId = createBookingServiceResponse.getResult().getBookingId();
    }

    @Given("I have previously created booking")
    public void prepareUpdateBookingObject() {
        BookingDatesDto bookingDatesDto = BookingDatesDto.createBookingDatesDto("2015-07-22", "2015-07-25");
        updateBookingDto = BookingDto.createBookingDto("testFirstName updated", "testLastName updated", 75, false, "Spa", bookingDatesDto);
    }

    @And("I have authorisation data")
    public void prepareAuthorisationData() {
        authToken = ReadPropertyFromConfig.getAuthToken();
    }

    @When("I send update booking http request")
    public void sendUpdateBookingRequest() throws Exception {
        updateBookingServiceResponse = serviceClient.updateBooking(authToken, bookingId, updateBookingDto);
        bookingResource = updateBookingServiceResponse.getResult();
        responseCode = updateBookingServiceResponse.getStatusCode();
    }

    @And("Response body object booking matches update booking request object")
    public void checkBookingResponseBookingObject() {
        BookingResource updateBookingResource = updateBookingServiceResponse.getResult();

        assertThat(bookingResource.getFirstName()).isEqualTo(updateBookingDto.getFirstName());
        assertThat(bookingResource.getLastName()).isEqualTo(updateBookingDto.getLastName());
        assertThat(bookingResource.getAdditionalNeeds()).isEqualTo(updateBookingDto.getAdditionalNeeds());
        assertThat(bookingResource.getTotalPrice()).isEqualTo(updateBookingDto.getTotalPrice());
        assertThat(bookingResource.getDepositPaid()).isEqualTo(updateBookingDto.getDepositPaid());
        assertThat(bookingResource.getBookingDates()).isNotNull();
        assertThat(bookingResource.getBookingDates().getCheckIn()).isEqualTo(updateBookingDto.getBookingDates().getCheckIn());
        assertThat(bookingResource.getBookingDates().getCheckOut()).isEqualTo(updateBookingDto.getBookingDates().getCheckOut());
    }

    @When("I send get booking http request")
    public void sendGetBookingRequest() throws Exception {
        getBookingServiceResponse = serviceClient.getBooking(bookingId);
        bookingResource = getBookingServiceResponse.getResult();
        responseCode = getBookingServiceResponse.getStatusCode();
    }

    @When("I send delete booking http request")
    public void sendDeleteBookingRequest() throws Exception {
        deleteBookingServiceResponse = serviceClient.deleteBooking(authToken, bookingId);
        responseCode = deleteBookingServiceResponse.getStatusCode();
    }
}

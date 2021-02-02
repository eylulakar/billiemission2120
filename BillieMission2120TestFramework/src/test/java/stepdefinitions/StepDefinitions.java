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
import webservice.serviceclient.ServiceResponse;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScoped
public class StepDefinitions {
    private final BookingServiceClient serviceClient = new BookingServiceClient();

    private static String authToken;
    private static String currentKey = "default";
    private static Map<String, BookingDto> requestObjects = new HashMap<>();
    private static Map<String, Object> responseObjects = new HashMap<>();
    private static boolean isException = false;

    @Given("^I set create booking request object (.+)$")
    public void prepareCreateBookingObject(String scenario) throws Exception {
        currentKey = scenario;
        BookingDatesDto bookingDatesDto = BookingDatesDto.createBookingDatesDto("2015-07-18", "2015-07-20");
        BookingDto createBookingDto = BookingDto.createBookingDto("testFirstName", "testLastName", 50, true, "Sports",
                bookingDatesDto);
        requestObjects.put(currentKey, createBookingDto);
    }

    @When("I send create booking http request")
    public void sendCreateBookingRequest() throws Exception {
        BookingServiceResponse<CreateBookingResource> createBookingServiceResponse =
                serviceClient.createBooking(requestObjects.get(currentKey));
        responseObjects.put(currentKey, createBookingServiceResponse);
    }

    @Then("Response status should be {int}")
    public void checkResponseStatus(int statusCode) {
        ServiceResponse serviceResponse = ((ServiceResponse) responseObjects.get(currentKey));

        assertThat(serviceResponse.getStatusCode()).isEqualTo(statusCode);
    }

    @And("Response contains correct booking information")
    public void checkCreateBookingResponseBookingObject() {
        BookingServiceResponse<CreateBookingResource> createBookingResponse = (BookingServiceResponse<CreateBookingResource>) responseObjects.get(currentKey);
        BookingResource bookingResource = createBookingResponse.getResult().getBookingResource();

        BookingDto createBookingDto = requestObjects.get(currentKey);

        assertThat(bookingResource.getFirstName()).isEqualTo(createBookingDto.getFirstName());
        assertThat(bookingResource.getLastName()).isEqualTo(createBookingDto.getLastName());
        assertThat(bookingResource.getAdditionalNeeds()).isEqualTo(createBookingDto.getAdditionalNeeds());
        assertThat(bookingResource.getTotalPrice().toString()).isEqualTo(createBookingDto.getTotalPrice());
        assertThat(bookingResource.getDepositPaid()).isEqualTo(createBookingDto.getDepositPaid());
        assertThat(bookingResource.getBookingDates()).isNotNull();
        assertThat(bookingResource.getBookingDates().getCheckIn()).isEqualTo(createBookingDto.getBookingDates().getCheckIn());
        assertThat(bookingResource.getBookingDates().getCheckOut()).isEqualTo(createBookingDto.getBookingDates().getCheckOut());
    }

    @And("Response has non-null id value")
    public void checkCreateBookingResponseId() {
        BookingServiceResponse<CreateBookingResource> createBookingResponse = (BookingServiceResponse<CreateBookingResource>) responseObjects.get(currentKey);
        Integer bookingId = createBookingResponse.getResult().getBookingId();

        assertThat(bookingId).isNotNull();
    }

    @And("^Set total price to (.+)$")
    public void setPrice(String price) {
        requestObjects.get(currentKey);
    }

    @Given("I have previously created booking")
    public void prepareUpdateBookingObject() {
        BookingDatesDto bookingDatesDto = BookingDatesDto.createBookingDatesDto("2015-07-22", "2015-07-25");
        BookingDto updateBookingDto = BookingDto.createBookingDto("testFirstName updated", "testLastName updated", 75,
                false, "Spa", bookingDatesDto);
        requestObjects.put(currentKey, updateBookingDto);
    }

    @And("I have authorisation data")
    public void prepareAuthorisationData() {
        authToken = ReadPropertyFromConfig.getAuthToken();
    }

    @When("I send update booking http request")
    public void sendUpdateBookingRequest() throws Exception {
        BookingServiceResponse<CreateBookingResource> createBookingResponse = (BookingServiceResponse<CreateBookingResource>) responseObjects.get(currentKey);
        BookingDto updateBookingDto = requestObjects.get(currentKey);

        BookingServiceResponse<BookingResource> updateBookingServiceResponse;

        try {
            updateBookingServiceResponse = serviceClient.updateBooking(authToken, createBookingResponse.getResult().getBookingId(),
                    updateBookingDto);
            responseObjects.put(currentKey, updateBookingServiceResponse);
            isException = false;
        } catch (Exception e) {
            isException = true;
        }

    }

    @And("Should throw exception")
    public void hasException() {
        assertThat(isException).isTrue();
    }

    @And("Response body object booking matches update booking request object")
    public void checkBookingResponseBookingObject() {
        BookingServiceResponse<BookingResource> updateBookingServiceResponse = (BookingServiceResponse<BookingResource>) responseObjects.get(currentKey);
        BookingDto updateBookingDto = requestObjects.get(currentKey);
        BookingResource bookingResource = updateBookingServiceResponse.getResult();

        assertThat(bookingResource.getFirstName()).isEqualTo(updateBookingDto.getFirstName());
        assertThat(bookingResource.getLastName()).isEqualTo(updateBookingDto.getLastName());
        assertThat(bookingResource.getAdditionalNeeds()).isEqualTo(updateBookingDto.getAdditionalNeeds());
        assertThat(bookingResource.getTotalPrice().toString()).isEqualTo(updateBookingDto.getTotalPrice());
        assertThat(bookingResource.getDepositPaid()).isEqualTo(updateBookingDto.getDepositPaid());
        assertThat(bookingResource.getBookingDates()).isNotNull();
        assertThat(bookingResource.getBookingDates().getCheckIn()).isEqualTo(updateBookingDto.getBookingDates().getCheckIn());
        assertThat(bookingResource.getBookingDates().getCheckOut()).isEqualTo(updateBookingDto.getBookingDates().getCheckOut());
    }

    @When("I send get booking http request")
    public void sendGetBookingRequest() throws Exception {
        BookingServiceResponse<CreateBookingResource> createBookingResponse = (BookingServiceResponse<CreateBookingResource>) responseObjects.get(currentKey);

        try {
            BookingServiceResponse<BookingResource> getBookingServiceResponse =
                    serviceClient.getBooking(createBookingResponse.getResult().getBookingId());
            responseObjects.put(currentKey, getBookingServiceResponse);
            isException = false;
        }catch (Exception e){
            isException = true;
        }

    }

    @When("I send delete booking http request")
    public void sendDeleteBookingRequest() throws Exception {
        BookingServiceResponse<CreateBookingResource> createBookingResponse = (BookingServiceResponse<CreateBookingResource>) responseObjects.get(currentKey);

        BookingServiceResponse<?> deleteBookingServiceResponse = serviceClient.deleteBooking(authToken,
                createBookingResponse.getResult().getBookingId());
        responseObjects.put(currentKey, deleteBookingServiceResponse);
    }

    @And("^I change dates to (.+) and (.+)$")
    public void prepareBookingDates(String checkIn, String checkOut) {
        BookingDto bookingDto = requestObjects.get(currentKey);
        bookingDto.setBookingDates(BookingDatesDto.createBookingDatesDto(checkIn, checkOut));
        requestObjects.put(currentKey, bookingDto);
    }
}

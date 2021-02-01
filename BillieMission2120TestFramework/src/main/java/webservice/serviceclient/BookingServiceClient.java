package webservice.serviceclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import utils.ReadPropertyFromConfig;
import webservice.data.dto.BookingDto;
import webservice.data.resource.BookingResource;
import webservice.data.resource.CreateBookingResource;

public class BookingServiceClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String applicationJsonString = "application/json";
    public static final MediaType MediaTypeJSON
            = MediaType.parse("application/json; charset=utf-8");

    private String serviceUrl;
    private OkHttpClient httpClient;
    private Request request;

    public BookingServiceClient() {
        this.httpClient = new OkHttpClient().newBuilder()
                .build();
        this.serviceUrl = ReadPropertyFromConfig.getServiceUrl();
    }

    /**
     * Creates cooking with specified values, returns created booking and response code.
     *
     * @param bookingDto
     * @return BookingServiceResponse<CreateBookingResource>
     * @throws Exception
     */
    public BookingServiceResponse<CreateBookingResource> createBooking(final BookingDto bookingDto) throws Exception {
        RequestBody body = RequestBody.create(OBJECT_MAPPER.writeValueAsString(bookingDto), MediaTypeJSON);
        Request request = new Request.Builder()
                .url(serviceUrl + "/booking")
                .method("POST", body)
                .addHeader("Content-Type", applicationJsonString)
                .addHeader("Accept", applicationJsonString)
                .build();
        Response response = httpClient.newCall(request).execute();
        String bodyString = response.body().string();
        CreateBookingResource createBookingResource = OBJECT_MAPPER.readValue(bodyString, CreateBookingResource.class);
        return new BookingServiceResponse(createBookingResource, response.code());
    }

    /**
     * Updates booking with specified values, returns updated booking and response code.
     *
     * @param authToken  string
     * @param bookingId  int
     * @param bookingDto
     * @return BookingServiceResponse<BookingResource>
     * @throws Exception
     */
    public BookingServiceResponse<BookingResource> updateBooking(final String authToken, final int bookingId, final BookingDto bookingDto) throws Exception {
        RequestBody body = RequestBody.create(OBJECT_MAPPER.writeValueAsString(bookingDto), MediaTypeJSON);
        Request request = new Request.Builder()
                .url(serviceUrl + "/booking/" + bookingId)
                .method("PUT", body)
                .addHeader("Content-Type", applicationJsonString)
                .addHeader("Accept", applicationJsonString)
                .addHeader("Authorization", "Basic " + authToken)
                .build();
        Response response = httpClient.newCall(request).execute();
        String bodyString = response.body().string();
        BookingResource bookingResource = OBJECT_MAPPER.readValue(bodyString, BookingResource.class);
        return new BookingServiceResponse(bookingResource, response.code());
    }

    /**
     * Gets booking by id, returns booking and response code
     *
     * @param bookingId int
     * @return BookingServiceResponse<BookingResource>
     * @throws Exception
     */
    public BookingServiceResponse<BookingResource> getBooking(final int bookingId) throws Exception {
        request = new Request.Builder()
                .url(serviceUrl + "/booking/" + bookingId)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .build();
        Response response = httpClient.newCall(request).execute();
        String bodyString = response.body().string();
        BookingResource bookingResource = new BookingResource();
        if (!bodyString.equals("Not Found")) {
            bookingResource = OBJECT_MAPPER.readValue(bodyString, BookingResource.class);
        }
        return new BookingServiceResponse(bookingResource, response.code());
    }

    /**
     * Deletes booking by id, returns response code.
     *
     * @param authToken string
     * @param bookingId int
     * @return BookingServiceResponse
     * @throws Exception
     */
    public BookingServiceResponse<?> deleteBooking(final String authToken, final int bookingId) throws Exception {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);

        request = new Request.Builder()
                .url(serviceUrl + "/booking/" + bookingId)
                .method("DELETE", body)
                .addHeader("Authorization", "Basic " + authToken)
                .build();
        Response response = httpClient.newCall(request).execute();
        return new BookingServiceResponse(null, response.code());
    }
}
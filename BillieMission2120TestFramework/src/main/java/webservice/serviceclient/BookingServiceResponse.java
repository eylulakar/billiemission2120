package webservice.serviceclient;

public class BookingServiceResponse<T> extends ServiceResponse {

    private T _body;

    public BookingServiceResponse(T body, int statusCode) {
        _body = body;
        _statusCode = statusCode;
    }

    public T getResult() {
        return _body;
    }
}

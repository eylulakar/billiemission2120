package webservice.serviceclient;

public class BookingServiceResponse<T> {

    private T _body;
    private int _statusCode;

    public BookingServiceResponse(T body, int statusCode) {
        _body = body;
        _statusCode = statusCode;
    }

    public T getResult() {
        return _body;
    }

    public int getStatusCode() {
        return _statusCode;
    }
}

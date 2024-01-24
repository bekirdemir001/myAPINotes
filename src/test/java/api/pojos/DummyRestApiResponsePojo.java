package api.pojos;

public class DummyRestApiResponsePojo {
    private String status;
    private DummyRestApiDataPojo data;
    private String message;

    public DummyRestApiResponsePojo() {
    }

    public DummyRestApiResponsePojo(String status, DummyRestApiDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public DummyRestApiDataPojo getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DummyRestApiResponsePojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
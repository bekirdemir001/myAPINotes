package api.pojos.gorestpojos;

public class GoRestPojo {
    private Object meta;
    private GoRestDataPojo data;

    public GoRestPojo() {
    }

    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    @Override
    public String toString() {
        return "GoRestResponsePojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
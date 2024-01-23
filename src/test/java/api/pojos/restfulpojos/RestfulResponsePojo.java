package api.pojos.restfulpojos;

public class RestfulResponsePojo {
    private Integer bookingid;
    private RestfulBookingPojo booking;

    public RestfulResponsePojo() {
    }

    public RestfulResponsePojo(Integer bookingid, RestfulBookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public RestfulBookingPojo getBooking() {
        return booking;
    }

    @Override
    public String toString() {
        return "RestfulResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
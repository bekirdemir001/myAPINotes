package api.pojos.restfulpojos;

public class RestfulBookingDatesPojo {
    private String checkin;
    private String checkout;

    public RestfulBookingDatesPojo() {
    }

    public RestfulBookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    @Override
    public String toString() {
        return "RestfulBookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
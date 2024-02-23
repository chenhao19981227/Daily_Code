package reconstruction.chapter12.replace_subclass_with_delegate.after;

public class Test {
    public static void main(String[] args) {
        Show show=new Show(100,true);
        Booking booking = Booking.createBooking(show, "6");
        System.out.println(booking);
        System.out.println(booking.basePrice());
        Booking premiumBooking = Booking.createBooking(show, "6");
        premiumBooking.bePremium(new Extras(20,true));
        System.out.println(premiumBooking);
        System.out.println(premiumBooking.basePrice());
        System.out.println(premiumBooking.hasDinner());
    }
}

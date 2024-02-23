package reconstruction.chapter12.replace_subclass_with_delegate.before;

public class Test {
    public static void main(String[] args) {
        Show show=new Show(100,true);
        Booking booking=new Booking(show,"6");
        System.out.println(booking);
        System.out.println(booking.basePrice());

        PremiumBooking premiumBooking=new PremiumBooking(show,"6",new Extras(20,true));
        System.out.println(premiumBooking);
        System.out.println(premiumBooking.basePrice());
        System.out.println(premiumBooking.hasDinner());
    }
}

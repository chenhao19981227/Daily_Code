package reconstruction.chapter12.replace_subclass_with_delegate.after;

import lombok.ToString;

@ToString
public class Booking {
    Show show;
    String date;
    PremiumBookingDelegate premiumBookingDelegate;
    public Booking(Show show, String date) {
        this.show = show;
        this.date = date;
    }
    public void bePremium(Extras extras){
        this.premiumBookingDelegate=new PremiumBookingDelegate(this,extras);
    }
    public boolean hasTalkback(){
        if(premiumBookingDelegate!=null){
            return show.talkback;
        }else {
            return show.talkback&&!isPeakDay();
        }
    }
    public double basePrice(){
        double result=show.price;
        if(isPeakDay())result+=Math.round(result*0.15);
        return premiumBookingDelegate==null?result:premiumBookingDelegate.extendBasePrice(result);
    }
    protected boolean isPeakDay() {
        return date.equals("7")||date.equals("6");
    }

    public boolean hasDinner(){
        if(this.premiumBookingDelegate==null){
            return false;
        }
        return premiumBookingDelegate.hasDinner();
    }
    public static Booking createBooking(Show show,String date){
        return new Booking(show,date);
    }
}

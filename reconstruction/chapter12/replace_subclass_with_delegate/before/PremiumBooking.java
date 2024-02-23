package reconstruction.chapter12.replace_subclass_with_delegate.before;

import lombok.ToString;

@ToString
public class PremiumBooking extends Booking{
    Extras extras;
    public PremiumBooking(Show show, String date,Extras extras) {
        super(show, date);
        this.extras=extras;
    }
    @Override
    public boolean hasTalkback() {
        return show.talkback;
    }

    @Override
    public double basePrice() {
        return Math.round(super.basePrice()+extras.premiumFee);
    }
    public boolean hasDinner(){
        return extras.hasDinner&&!isPeakDay();
    }
}

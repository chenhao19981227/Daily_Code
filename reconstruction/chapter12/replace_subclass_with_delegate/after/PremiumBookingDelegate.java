package reconstruction.chapter12.replace_subclass_with_delegate.after;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PremiumBookingDelegate {
    Booking booking;
    Extras extras;
    public double extendBasePrice(double basePrice){
        return Math.round(basePrice+extras.premiumFee);
    }
    public boolean hasDinner(){
        return extras.hasDinner&&!booking.isPeakDay();
    }

    @Override
    public String toString() {
        return "PremiumBookingDelegate{" +
                "extras=" + extras +
                '}';
    }
}

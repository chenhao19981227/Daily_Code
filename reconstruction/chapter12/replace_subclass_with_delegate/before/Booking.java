package reconstruction.chapter12.replace_subclass_with_delegate.before;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Booking {
    Show show;
    String date;
    public boolean hasTalkback(){
        return show.talkback&&!this.isPeakDay();
    }
    public double basePrice(){
        double result=show.price;
        if(isPeakDay())result+=Math.round(result*0.15);
        return result;
    }

    protected boolean isPeakDay() {
        return date.equals("7")||date.equals("6");
    }
}

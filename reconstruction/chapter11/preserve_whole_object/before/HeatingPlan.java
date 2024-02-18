package reconstruction.chapter11.preserve_whole_object.before;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeatingPlan {
    TemperatureRange temperatureRange;
    public boolean withRange(double high,double low){
        return low>=this.temperatureRange.getLow()&&high<=this.temperatureRange.getHigh();
    }
}

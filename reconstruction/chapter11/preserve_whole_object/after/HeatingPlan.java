package reconstruction.chapter11.preserve_whole_object.after;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeatingPlan {
    TemperatureRange temperatureRange;
    public boolean withRange(TemperatureRange range){
        return range.getLow()>=this.temperatureRange.getLow()&&range.getHigh()<=this.temperatureRange.getHigh();
    }

}

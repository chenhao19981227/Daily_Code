package reconstruction.chapter10.decompose_conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    int summerStart;
    int summerEnd;
    double summerRate;
    double regularRate;
    double regularServiceCharge;
}

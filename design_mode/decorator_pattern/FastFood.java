package design_mode.decorator_pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class FastFood {
    private float price;
    private String desc;
    public abstract float cost();
}

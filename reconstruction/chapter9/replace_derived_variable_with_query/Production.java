package reconstruction.chapter9.replace_derived_variable_with_query;

import java.util.ArrayList;
import java.util.List;

public class Production {
    private int price;
    private List<Integer> priceChange;
    public Production(int price){
        this.price=price;
        this.priceChange=new ArrayList<>();
    }
    public int getPrice() {
        return price;
    }
    public void applyPriceChange(int change){
        priceChange.add(change);
        this.price-=change;
    }
}

package reconstruction.chapter9.replace_derived_variable_with_query;

import java.util.ArrayList;
import java.util.List;

public class ProductionAfterRDVWQ {
    private int initialPrice;
    private List<Integer> priceChange;
    public ProductionAfterRDVWQ(int initialPrice){
        this.initialPrice=initialPrice;
        this.priceChange=new ArrayList<>();
    }
    public int getPrice(){
        return this.initialPrice-calTotalChange();
    }
    public void applyPriceChange(int change){
        priceChange.add(change);
    }
    public int calTotalChange(){
        int totalChange=0;
        for (Integer i : this.priceChange) {
            totalChange+=i;
        }
        return totalChange;
    }
}

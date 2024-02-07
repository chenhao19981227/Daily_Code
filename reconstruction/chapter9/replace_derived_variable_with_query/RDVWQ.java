package reconstruction.chapter9.replace_derived_variable_with_query;

public class RDVWQ {
    public static void main(String[] args) {
        Production production=new Production(100);
        production.applyPriceChange(10);
        production.applyPriceChange(20);
        System.out.println(production.getPrice());
        System.out.println("-----------------------");
        ProductionAfterRDVWQ productionAfterRDVWQ=new ProductionAfterRDVWQ(100);
        productionAfterRDVWQ.applyPriceChange(10);
        productionAfterRDVWQ.applyPriceChange(20);
        System.out.println(production.getPrice());
    }
}

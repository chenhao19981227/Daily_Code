package design_mode.template_pattern;

public abstract class AbstractClass {
    
    public final void cookProcess() {
        this.pourOil();
        this.heatOil();
        this.pourVegetable();
        this.pourSauce();
        this.fry();
    }

    public void pourOil() {
        System.out.println("倒油");
    }

    public void heatOil() {
        System.out.println("热油");
    }

    public abstract void pourVegetable();

    public abstract void pourSauce();

    public void fry(){
        System.out.println("炒啊炒啊炒到熟啊");
    }
}

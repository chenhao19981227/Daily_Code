package reconstruction.chapter12.replace_superclass_with_delegate.after;

import java.util.Map;

public class Scroll {
    private int id;
    private int dateLastCleaned;
    private CatalogItem catalogItem;
    public Scroll(int id, int dateLastCleaned, Map<Integer,CatalogItem> catalog,int catalogID) {
        this.id=id;
        this.dateLastCleaned=dateLastCleaned;
        this.catalogItem=catalog.get(catalogID);
    }
    public boolean hasTags(String tag){
        return catalogItem.hasTags(tag);
    }
    public int getId() {
        return catalogItem.getId();
    }
    public String getTitle() {
        return catalogItem.getTitle();
    }
    public boolean isNeedClean(int targetDate){
        return dateLastCleaned>targetDate;
    }
}

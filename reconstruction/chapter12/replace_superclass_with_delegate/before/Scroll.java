package reconstruction.chapter12.replace_superclass_with_delegate.before;

import java.util.List;

public class Scroll extends CatalogItem{
    private int dateLastCleaned;
    public Scroll(int id, String title, List<String> tags,int dateLastCleaned) {
        super(id, title, tags);
        this.dateLastCleaned=dateLastCleaned;
    }
    public boolean isNeedClean(int targetDate){
        return dateLastCleaned>targetDate;
    }
}

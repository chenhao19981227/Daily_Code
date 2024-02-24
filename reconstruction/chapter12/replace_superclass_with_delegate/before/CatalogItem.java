package reconstruction.chapter12.replace_superclass_with_delegate.before;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CatalogItem {
    private int id;
    private String title;
    private List<String> tags;

    public boolean hasTags(String tag){
        return tags.contains(tag);
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

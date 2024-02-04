package reconstruction.chapter9.rename_field;

public class Organization {
    private String title;
    private String country;

    public Organization(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

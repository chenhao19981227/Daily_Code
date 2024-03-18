package effective_java.chapter4_classes_and_interfaces.item21;

public class User implements IUser{
    private String name;

    private String password;
    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

package effective_java.chapter4_classes_and_interfaces.item21;

import java.util.Objects;

public class MyUser implements IUser {
    private String name;

    private String password;

    private String tmp;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        if (password != null)
            this.tmp = password;
    }

    public void confirmPassword(String password) {
        if (Objects.equals(tmp, password)) {
            this.password = password;
            this.tmp = null;
        }
    }
}
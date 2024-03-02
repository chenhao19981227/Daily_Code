package effective_java.chapter3_methods_common_to_all_objects.item13;

public class SubUser extends User {
    String[] address;

    public SubUser(int id, String name, String[] address) {
        super(id, name);
        this.address = address;
    }

    public SubUser(String[] address) {
        this.address = address;
    }

    @Override
    public SubUser clone() {
        SubUser user = (SubUser) super.clone();
        user.address=address.clone();
        return user;
    }

    @Override
    public String toString() {
        return "SubUser{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

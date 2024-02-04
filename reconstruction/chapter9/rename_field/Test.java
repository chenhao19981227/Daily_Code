package reconstruction.chapter9.rename_field;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String > organization =new HashMap<>();
        organization.put("name","ChenHao");
        organization.put("country","CN");
        System.out.println(organization.get("name"));
        System.out.println("--------------------");
        Organization organizationRenameField=new Organization("ChenHao","CN");
        System.out.println(organizationRenameField.getTitle());
    }
}

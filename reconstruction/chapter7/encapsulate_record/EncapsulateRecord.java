package reconstruction.chapter7.encapsulate_record;

import java.util.HashMap;
import java.util.Map;

public class EncapsulateRecord {

    public static void main(String[] args) {
        Map<String,String> organization=new HashMap<>();
        organization.put("name","ChenHao");
        organization.put("country","CN");
        // 读取和更新
        System.out.println(organization.get("name"));
        organization.put("name","newName");
        System.out.println(organization.get("name"));

        System.out.println("-------------------------");
        Organization organization1=new Organization("ChenHao","CN");
        System.out.println(organization1.getName());
        organization1.setName("newName");
        System.out.println(organization1.getName());
    }
}

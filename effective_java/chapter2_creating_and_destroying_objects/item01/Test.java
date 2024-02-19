package effective_java.chapter2_creating_and_destroying_objects.item01;

public class Test {
    public static void main(String[] args) {
        Cat cat1=new Cat();
        Cat cat2 = Cat.getInstance();

        Cat tomcat1 = new Cat(1,true, "cat");
        Cat tomcat2=Cat.getTomcat(1,"cat");

        Cat singleNameCat1=Cat.getNameNoRepeat("kitty",true);
        Cat singleNameCat2=Cat.getNameNoRepeat("kitty",true);
        System.out.println(singleNameCat1==singleNameCat2);

        Cat spyCat = Cat.getSpyCat();

        System.out.println(Cat.getCatByNameLength("tom").getClass());
        System.out.println(Cat.getCatByNameLength("CrookShanks").getClass());

        CatServices.registerProvider("eat",EatService::new);
        EatService eat = (EatService) CatServices.newInstance("eat");
        eat.execute();
        Service sleep = CatServices.newInstance("Sleep");
    }
}

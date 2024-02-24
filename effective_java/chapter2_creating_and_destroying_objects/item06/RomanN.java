package effective_java.chapter2_creating_and_destroying_objects.item06;

public class RomanN {
    public boolean isRomanNumeral(String originString){
        return originString.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
    private static final String ROMANREGEX = "^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
    public boolean isRomanNumeral2(String originString){
        return originString.matches(ROMANREGEX);
    }
}

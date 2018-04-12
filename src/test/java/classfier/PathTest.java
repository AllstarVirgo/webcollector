package classfier;

public class PathTest {
    public static void main(String[] args) {
        StringBuilder i=new StringBuilder("potato");
        setA(i);
        System.out.println(i);
    }

    public static void setA(StringBuilder i){
        i.append(" jasmine");
    }
}

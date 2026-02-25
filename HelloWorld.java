import java.util.List;
import java.util.ArrayList;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        var x = new ArrayList<Integer>();
        System.out.println(x instanceof List);
        MyRecord rec = new MyRecord(5);
        System.out.println(rec);
        System.out.println(rec.y());
    }
}

record MyRecord(int y) {}
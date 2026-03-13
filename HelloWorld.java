import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        
        var x = new ArrayList<Integer>();
        System.out.println(x instanceof List);
        
        MyRecord2 rec2 = new MyRecord2(5);
        System.out.println(rec2.x);
        
        MyRecord rec = new MyRecord(5);
        System.out.println(rec);
        System.out.println(rec.y());
        
        List<String> names = Arrays.asList("Sachin", "Dhanoriya", "Ira", "Zeha");
        names.set(0, null);
        System.out.println(names);
        
        List<String> name = List.of("null");
        System.out.println(name);
        name.set(0,"null");

    }
    record MyRecord2(int x) {}
}

record MyRecord(int y) {}
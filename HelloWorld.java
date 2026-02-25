public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        var x = 1;
        System.out.println(x instanceof int);
        MyRecord rec = new MyRecord(5);
        System.out.println(rec);
        System.out.println(rec.y());
    }
}

record MyRecord(int y) {}
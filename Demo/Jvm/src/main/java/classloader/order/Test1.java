package classloader.order;

public class Test1 {
    public String name;
    private final static Test1 t2 = new Test1("1");;
    Test1 t3 = t2;
    static {
        Test2 test2 = new Test2();
        System.out.println("test1");
        test2.t();
    }

    public void setT3(Test1 t3) {
        this.t3 = t3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test1(String name){
        this.name = name;
    }
    public static void main(String[] args) {
        System.out.println("test1 main");
        System.out.println(t2);
        t2.setName("3");
        System.out.println(t2);

    }

    @Override
    public String toString() {
        return "Test1{" +
                "name='" + name + '\'' +
                '}';
    }
}

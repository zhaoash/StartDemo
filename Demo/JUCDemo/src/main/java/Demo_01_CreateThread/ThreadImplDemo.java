package Demo_01_CreateThread;

public class ThreadImplDemo {

    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("thread-01");
            }
        };
        t.setName("thread-01");
        t.start();
        System.out.println("main");
    }
}

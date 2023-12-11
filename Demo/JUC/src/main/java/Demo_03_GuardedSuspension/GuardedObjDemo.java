package Demo_03_GuardedSuspension;

public class GuardedObjDemo {

    private Object content;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (true) {

            }
        });
        Thread.yield();
        thread.start();

    }
}

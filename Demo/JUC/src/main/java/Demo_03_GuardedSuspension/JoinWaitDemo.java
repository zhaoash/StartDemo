package Demo_03_GuardedSuspension;

public class JoinWaitDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread waitJoin = new Thread(() -> {
            try {
                System.out.println("wait join");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        waitJoin.start();

        new Thread(()->{
            try {
                synchronized (JoinWaitDemo.class){
                    System.out.println("before wait");
                    JoinWaitDemo.class.wait();
                    System.out.println("after wait");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("before join");
        waitJoin.join();
        System.out.println("after join");
    }
}

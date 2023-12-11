package Demo_01_CreateThread;

public class RunnableDemo {
    public static void main(String[] args) {
        //使用实现Runnable接口方式的优点：使线程和任务相分离，任务可以作为参数传进去
        new Thread(
                //使用lambda表达式实现Runnable接口，带有FunctionalInterface注解的接口可以使用lambda
                ()-> System.out.println("thread-01")
                ,"thread-01"
        ).start();
    }
}

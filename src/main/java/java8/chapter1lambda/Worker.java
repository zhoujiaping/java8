package java8.chapter1lambda;


public class Worker implements Runnable{

    @Override
    public void run() {
        System.out.println("外部类方式");
    }
    
}

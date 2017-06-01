package java8.chapter1lambda;


public interface Singable {
    default void sing(){
        System.out.println("yeah, i can sing a song!");
    }
    static void laughing(){
        System.out.println("呵呵");
    }
}

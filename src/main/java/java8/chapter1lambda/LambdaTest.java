package java8.chapter1lambda;

import java.awt.Button;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class LambdaTest {

    // lambda
    @Test
    public void demo1_1() {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
    }

    @Test
    public void demo1_2() {
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("匿名对象方式");
            }
        });
        t2.start();
    }

    @Test
    public void demo1_3() {
        Thread t3 = new Thread(() -> {
            System.out.println("lambda方式");
        });
        t3.start();
    }

    @Test
    public void demo2_1() {
        String[] strings = {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        Arrays.sort(strings, new LengthComparator());
        //Arrays.sort(strings);
        // System.out.println(strings);//[Ljava.lang.String;@30946e09
        Stream.of(strings).forEach(System.out::println);
        // 编译器会创建一个Comparator接口的实例
        // Comparator的源代码
        /*
         * @FunctionalInterface只含有一个方法的接口，都是函数式接口 public interface Comparator<T> {
         */
        //使用list的forEach
        //Arrays.asList(strings).forEach(System.out::println);;
    }

    @Test
    public void demo2_2() {
        String[] strings = {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        Arrays.sort(strings, (x, y) -> Integer.compare(x.length(), y.length()));
        Stream.of(strings).forEach(System.out::println);
    }

    @Test
    public void demo2_3() {
        String[] strings = {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        Comparator<String> c = (x, y) -> Integer.compare(x.length(), y.length());
        Arrays.sort(strings, c);
        Stream.of(strings).forEach(System.out::println);
    }

    // 方法引用
    @Test
    public void demo3_1() {
        String[] strings = {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        // 本来传一个接口类型的地方，现在可以传一个方法
        Arrays.sort(strings, String::compareToIgnoreCase);
        Stream.of(strings).forEach(System.out::println);
    }

    // 构造器引用
    @Test
    public void demo4_1() {
        String[] labels = {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        Stream<Button> stream = Stream.of(labels).map(Button::new);
        List<Button> buttons = stream.collect(Collectors.toList());
        // 对比嵌套风格和链式风格
        buttons.stream().forEach(b -> System.out.println(b.getLabel()));
        //Stream.of(buttons.toArray(new Button[buttons.size()])).forEach(b -> System.out.println(b.getLabel()));
    }

    // 闭包
    @Test
    public void demo5_1() {
        hello(5);
    }

    public static void hello(int i) {
        Stream.of(new Integer[] {1, 2, 3 }).forEach(x -> {
            //访问了自由变量i，不能修改自由变量i
            System.out.println(x + i);
        });
    }
    //默认接口
    @Test
    public void demo6_1(){
        new Singable(){}.sing();
        new Singable(){
            public void sing(){
                System.out.println("gua gua gua");
            }
        }.sing();
    }
    //接口可以有静态方法
    /*以往array有arrays工具类
     * list和set有collections工具类
     * 现在可以将接口和该接口的工具类写一起了
     */
    @Test 
    public void demo7_1(){
        Singable.laughing();
    }
}

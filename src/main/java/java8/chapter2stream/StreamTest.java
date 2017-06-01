package java8.chapter2stream;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * stream不存储元素
 * stream不修改源对象，返回新的stream
 * stream可能并行执行
 */
public class StreamTest {

    // 创建stream
    @Test
    public void demo1_1() {
        // 集合collection
        List<String> names = Arrays.asList(new String[] {"David", "John", "Tamato", "avril lavigne", "taylor swift" });
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
        // 数组
        String[] arr = new String[] {"David", "John", "Tamato", "avril lavigne", "taylor swift" };
        Stream.of(arr).forEach(System.out::println);
        // 无限stream1 元素个数无限个
        // Stream.generate(Math::random).forEach(System.out::println);
        // 无限stream2
        // Stream.iterate(1, x->x+1).filter(x->x<20).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, x -> x.add(BigInteger.ONE)).forEach(System.out::println);
        // 从pattern创建
        String s = "1,2,3,4,5,5,6";
        Pattern.compile(",").splitAsStream(s).forEach(System.out::println);
        // 其他api
        Path path = Paths.get("d:/test");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 流转换
    @Test
    public void demo2_1() {
        Integer[] seed = new Integer[100];
        Random r = new Random();
        Stream<Integer> s = Stream.of(seed);
        // map
        Stream<Person> s1 = s.map((x) -> {
            x = r.nextInt(50);
            Person p = new Person();
            p.setName("" + x);
            p.setAge(x);
            p.setSalary(x);
            p.setWorkyear(x);
            return p;
        });
        // filter
        Stream<Person> s2 = s1.filter(x -> x.getAge() > 30);
        // foreach
        s2.forEach(x -> System.out.println(x.getAge()));
        // reduce
        float sumSalary = s2.map(Person::getSalary).reduce((x, y) -> x + y).get().floatValue();
        System.out.println(sumSalary);
    }

    // flatMap
    @Test
    public void demo3_1() {
        // 注意 Stream.of(原始类型数组)和Stream.of(包装类型数组)结果不一样
        String[] words = {"hello", "world" };
        Character[] ch = {'a', 'b', 'c' };
        Stream.of(ch);
        Stream.of(words).flatMap(x -> Arrays.asList(x.toCharArray()).stream()).forEach(x -> System.out.println(x));

        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }

    // 提取子流
    @Test
    public void demo4_1() {
        Stream.iterate(1, x -> x + 1).limit(100).forEach(System.out::println);
    }

    // 流合并
    @Test
    public void demo5_1() {
        Stream.concat(Stream.iterate(1, x -> x + 1).limit(100), Stream.iterate(201, x -> x + 1).limit(50))
                .forEach(System.out::println);
        ;
    }

    // optional
    @Test
    public void demo6_1() {
        String name = "test";
        //
        if (name != null) {
            System.out.println(name);
        }
        if (name != null) {
            System.out.println(name);
        } else {
            System.out.println("else...");
        }
        Integer len = null;
        if (name != null) {
            len = name.length();
        } else {
            len = null;
        }
        if (len != null) {
            System.out.println(name.length());
        }

        //
        Optional<String> op = Optional.of(name);
        // Optional<String> op = Optional.empty();
        op.ifPresent(System.out::println);
        System.out.println(op.orElse("else..."));
        op.map(x -> x.length()).ifPresent(System.out::println);
        //
        Person person = new Person();
        person.setDesc(Optional.of("judy"));
        Optional<Person> op2 = Optional.of(person);
        op2.flatMap(x -> x.getDesc()).ifPresent(System.out::println);
    }

    // 收集结果
    @Test
    public void demo7_1() {
        String[] names = {"a", "john", "sindy", "lufy", "" };
        //转数组
        String[] names2 = Stream.of(names).filter(x -> x.length() > 3).toArray(String[]::new);
        //转set
        Stream<String> stream = Stream.of(names).filter(x->x.length()>3);
        Set<String> set = stream.collect(Collectors.toSet());
        stream = Stream.of(names).filter(x->x.length()>3);
        set = stream.collect(TreeSet::new,TreeSet::add,TreeSet::addAll);
        //转list
        stream = Stream.of(names).filter(x->x.length()>3);
        List<String> list = stream.collect(Collectors.toList());
        //treeset
        stream = Stream.of(names).filter(x->x.length()>3);
        TreeSet<String> ts = stream.collect(Collectors.toCollection(TreeSet::new));
        //转map
        stream = Stream.of(names).filter(x->x.length()>3);
        Map<String,Integer> map = stream.collect(Collectors.toMap(x->x, x->x.length()));
        //统计
        //IntStream intStream = Stream.of(names).mapToInt(x->x.length());
        stream = Stream.of(names);
        IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));
        double avg = summary.getAverage();
        long sum = summary.getSum();
        int max = summary.getMax();
    }
    //原始类型流
    @Test
    public void demo8_1(){
        IntStream.range(1, 50);
    }

}

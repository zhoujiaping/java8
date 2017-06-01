package java8.stream;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		//集合->流
		String[] names = {"book","pig","horse","card","happy"};
		List<String> list = Arrays.asList(names);
		//filter count
		long count = list.stream().filter(w->w.length()>=5).count();
		//map reduce
		list.stream().map(w->w.length()).reduce((x,y)->x+y);
		//distinct
		list.stream().distinct();
		//match
		list.stream().allMatch(w->w.length()>2);
		//得到迭代器
		list.stream().iterator();
		System.out.println(count);
		//数组->流
		Stream<String> stream = Stream.of(names);
		count = stream.filter(w->w.length()>4).count();
		
		stream = Stream.of("hello","world","what","um...");
		//流->数组
		Arrays.stream(names, 0, 2).toArray(String[]::new);
		//构建常数流
		stream = Stream.generate(()->"echo");
		System.out.println(stream.limit(3).count());
		//构建随机数流
		Stream.generate(Math::random);
		//构建序列值流
		Stream.iterate(BigInteger.ZERO, n->n.add(BigInteger.ONE));
		//正则切割字符串得到流
		Pattern.compile(",").splitAsStream("1,2,3,4,5");
		//文件内容行流
		//try-catch-resources
		try(Stream<String> lines = Files.lines(Paths.get("d:/test.log")) ){
			//流元素的遍历
			lines.forEach(System.out::println);
		}catch(Exception e){
		}
		//stream->集合
		List<String> collectList = list.stream().collect(Collectors.toList());
		System.out.println(collectList);
		//相当于js的数组的join
		list.stream().map(Object::toString).collect(Collectors.joining());
		//一次求和，平均值，最大值最小值
		list.stream().collect(Collectors.summarizingInt(String::length));
		//stream->map
		list.stream().collect(Collectors.toMap(w->w,String::length));
		list.stream().collect(Collectors.toMap(w->w,Function.identity()));
		
		//groupby  分组，计数，求和
		Map<Object,List<String>> map = list.stream().collect(Collectors.groupingBy(w->w.length()));
		System.out.println(map);
		//partition 
		Map<Boolean,List<String>> mapb = list.stream().collect(Collectors.partitioningBy(w->w.length()>4));
		System.out.println(mapb);
		Map<Boolean,Set<String>> maps = list.stream().collect(Collectors.partitioningBy(w->w.length()>4,Collectors.toSet()));
		System.out.println(maps);
		Map<Boolean,Long> mapl = list.stream().collect(Collectors.partitioningBy(w->w.length()>4,Collectors.counting()));
		System.out.println(mapl);
		list.stream().collect(Collectors.partitioningBy(w->w.length()>4,Collectors.summingInt(String::length)));
		
		//原始类型
		IntStream intstream = IntStream.of(1,2,3,4,5);
		intstream = Arrays.stream(new int[]{6,7,8,9,0});
		IntStream.range(0, 100);
		IntStream.rangeClosed(0, 99);
	}
}

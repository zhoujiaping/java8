package java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
public class Lambda {
	private String name;
	public String getName() {
		return name;
	}
	public Lambda(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		new Lambda("i am lambda").test();
	}
	public void test(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(2);
		list.sort(new Comparator<Integer>() {//这里接收接口类型，该接口只有一个方法。
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		final String desc = "when u r gone!";
		Comparator<Integer> comparator = (Integer x,Integer y)->Integer.compare(x, y);
		list.sort(comparator);
		list.sort(Integer::compare);//方法引用int java.lang.Integer.compare(int x, int y)
		list.sort(Integer::compareTo);//方法引用int java.lang.Integer.compareTo(Integer anotherInteger)
		list.sort((Integer x,Integer y)->Integer.compare(x, y));
		list.sort((Integer x,Integer y)->{return Integer.compare(x, y);});
		list.sort((x,y)->{
			System.out.println(desc);
			this.name = "...";
			System.out.println(this.getName());
			return Integer.compare(x, y);
		});
		System.out.println(list);
		list.toArray(new Integer[]{});
		list.forEach(new Consumer<Integer>(){
			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		});
		list.forEach(System.out::println);
	}
}

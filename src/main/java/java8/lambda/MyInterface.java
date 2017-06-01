package java8.lambda;

public interface MyInterface {
	default String getDesc(){
		return "this is a default method";
	}
}

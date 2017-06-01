package java8.chapter2stream;

import java.util.Optional;

public class Person {
    private String name;
    private int age;
    private float salary;
    private float workyear;
    private Optional<String> desc;
    
    public Optional<String> getDesc() {
        return desc;
    }
    
    public void setDesc(Optional<String> desc) {
        this.desc = desc;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public float getSalary() {
        return salary;
    }
    
    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    public float getWorkyear() {
        return workyear;
    }
    
    public void setWorkyear(float workyear) {
        this.workyear = workyear;
    }
    
}

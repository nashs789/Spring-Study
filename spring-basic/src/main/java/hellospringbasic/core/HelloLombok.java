package hellospringbasic.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("name");
        helloLombok.setAge(10);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();

        System.out.println(helloLombok);
    }
}

package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private int age;

    public UserDto() {
    }

    @QueryProjection
    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

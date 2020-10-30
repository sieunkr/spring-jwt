package com.example.demo.core;

import com.example.demo.core.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {

    private String id;
    private String firstName;
    private String lastName;
    private Role role;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public static MemberDTO of(User user) {
        return MemberDTO.builder()
                .id(String.valueOf(user.getId()))
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                //.role(user.getRole())
                .build();
    }
}
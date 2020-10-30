package com.example.demo.core;

import com.example.demo.core.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {

    private String id;
    private String userName;
    private String email;
    private Role role;

    public static MemberDTO of(User user) {
        return MemberDTO.builder()
                .id(String.valueOf(user.getId()))
                .userName(user.getUsername())
                .email(user.getEmail())
                //.role(user.getRole())
                .build();
    }
}
package com.example.demo.core;

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
}

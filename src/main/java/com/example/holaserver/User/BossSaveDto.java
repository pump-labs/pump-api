package com.example.holaserver.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BossSaveDto {
    private String name;
    private String email;
    private String phoneNumber;
    private Type type;
}

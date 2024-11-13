package com.habitual.demo.user.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPageDto {

    private String username;

    private String nickName;

    private String sex;

    private Long age;

    private String email;

    private String tel;

    private String role;

    private Long status;

    private int pageNum;

    private int pageSize;

}

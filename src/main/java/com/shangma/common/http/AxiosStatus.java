package com.shangma.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AxiosStatus {

    OK(20000,"操作成功"),
    ERROR(5555,"操作失败"),

    ERROR_SYSTEM_LOGIN_NOTFOUND(5410,"登录失败"),
    ERROR_SYSTEM_LOGIN_NOT(5411,"未登录"),
    ERROR_SYSTEM_ADD(5415,"添加失败"),
    ERROR_SYSTEM_PERMISSION(5416,"访问权限不足"),
    ERROR_DATABASE_OPTIONAL(5417,"数据库操作失败");

    private int status;

    private String message;

}

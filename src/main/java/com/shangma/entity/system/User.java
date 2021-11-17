package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_system_user")
public class User {
    private Long id;

    private String loginName;

    private String username;

    private String password;

    private Long departmentId;

    private Long sex;

    private Long status;

    private Long jobId;

    private String phone;

    private String email;

    private String ip;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;

    private Date createTime;

    private Long createId;

    private Date updateTime;

    private Long updateId;

    private String ext1;

    private String ext2;


}
package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_system_user")
public class User {

    @TableId(type = IdType.AUTO)
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

    private Long roleId;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;

    private LocalDateTime createTime;

    private Long createId;

    private LocalDateTime updateTime;

    private Long updateId;

    private String ext1;

    private String ext2;

    
}
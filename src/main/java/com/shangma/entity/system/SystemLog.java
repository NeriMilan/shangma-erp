package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_system_log")
public class SystemLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private LocalDateTime time;

    private String username;

    // 模块
    private String module;

    // 操作内容
    private String description;

    private Long departmentId;

    private String ip;

    private String loginName;

    private String ext1;

    private String ext2;

}
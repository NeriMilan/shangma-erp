package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_system_department")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime createTime;

    private Long createId;

    private LocalDateTime updateTime;

    private Long updateId;

    private String ext1;

    private String ext2;

}
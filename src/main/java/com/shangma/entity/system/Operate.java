package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_system_operate")
public class Operate {

    private Long id;

    private Long pid;

    private String name;

    private String location;

    private String description;

    private String ext1;

    private String ext2;

}
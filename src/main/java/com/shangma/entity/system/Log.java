package com.shangma.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName:Log
 * @Description: TODO
 * @Author:
 * @Date:2021/11/23 10:26
 * @Version: JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_log")
public class Log {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String userName;
    private String ip;
    private String method;
    private String args;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

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
@TableName("t_system_permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     权限描述
     */
    private String description;

    private Long status;

    /**
     <strong color='red'>可操作项( 对应 operate的location )</strong>
     */
    private String operate;

    private LocalDateTime createTime;

    private Long createId;

    private LocalDateTime updateTime;

    private Long updateId;

    /**
        <strong color='red'>     (权限范围, 可执行操作描述) 对应 operate的description
     </strong>
     */
    private String ext1;

    /**
        <strong color = 'red'>
     操作项id
     </strong>
     */
    private String ext2;

}
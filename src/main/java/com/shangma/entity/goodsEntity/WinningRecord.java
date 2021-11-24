package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_winning_record")
public class WinningRecord {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String contactPerson;

    private String contactAddress;

    private String contactPhone;

    private String idCard;

}

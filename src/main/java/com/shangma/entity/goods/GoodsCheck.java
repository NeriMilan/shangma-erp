package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_goods_check")
public class GoodsCheck {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long freeId;

    private Double shopPrice;

    private Integer changeStocks;

    private Integer checkState;

    private String checkOpinions;

    private Integer checkKind;

    private Long checkBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime checkTime;

}
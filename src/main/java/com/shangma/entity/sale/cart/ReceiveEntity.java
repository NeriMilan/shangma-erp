package com.shangma.entity.sale.cart;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @ClassName:ReceiveEntity
 * @Description: TODO
 * @Author:
 * @Date:2021/11/19 15:49
 * @Version: JDK1.8
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveEntity {
    @Builder.Default
    private Integer pageNum=1;
    @Builder.Default
    private Integer pageSize=2;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String goodName;
    private Integer itemStatus;
}

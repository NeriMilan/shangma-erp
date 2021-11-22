package com.shangma.entity.sale.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName:ReceiveEntity
 * @Description: TODO
 * @Author:
 * @Date:2021/11/19 15:49
 * @Version: JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveEntity {
    private Integer pageNum;
    private Integer pageSize;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String goodName;
    private Integer itemStatus;
}

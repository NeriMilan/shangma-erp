package com.shangma.entity.system.vo;

import com.shangma.entity.system.SystemLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Ryze
 * @create : 2021-11-24 10:12
 * @Description :
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SystemLogVO extends SystemLog {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}

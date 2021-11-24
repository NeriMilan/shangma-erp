package com.shangma.entity.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ryze
 * @create : 2021-11-23 16:29
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVO {

    private Long id;

    private String loginName;

    private String username;

    private String departmentName;

    private String sexName;

    private String statusName;

    private String jobName;

    private String roleName;

    private String permissionNames;

    private String scopes;


}

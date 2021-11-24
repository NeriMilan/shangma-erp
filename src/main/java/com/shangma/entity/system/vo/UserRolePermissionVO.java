package com.shangma.entity.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.Role;
import com.shangma.entity.system.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-23 11:56
 * @Description :
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRolePermissionVO extends User {

    private String departmentName;

    private String jobName;

    private String roleName;

    private List<String> permissionIdList;

    private List<String> permissionNameList;

    /**
        <strong color='red'>
        对应的是 Permission.ex1
     </strong>
     */
    private List<String> scopeList;

}

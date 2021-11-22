package com.shangma.entity.system.vo;

import com.shangma.entity.system.Permission;
import com.shangma.entity.system.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-19 10:11
 * @Description :
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO extends Role {

    private List<Permission> permissionList;

}

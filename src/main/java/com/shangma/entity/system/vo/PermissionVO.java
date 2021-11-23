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
 * @create : 2021-11-22 20:52
 * @Description :
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVO extends Permission {

    private List<Role> roleList;

}

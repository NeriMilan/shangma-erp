package com.shangma.entity.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.entity.system.Operate;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.tool.AbstComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-24 15:33
 * @Description :
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserVO {

    private Long id;

    private String loginName;

    private String username;

    private Long departmentId;

    private Long sex;

    private Long status;

    private Long jobId;

    private String phone;

    private String email;

    private String ip;

    private Long roleId;

    private List<Operate> operates;

}

package com.shangma.controller.system;

import com.github.pagehelper.PageHelper;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.RoleVO;
import com.shangma.service.system.RolePermissionService;
import com.shangma.service.system.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:25
 * @Description :
 */
@Slf4j
@RestController
@RequestMapping("/system/role")
public class RoleController {

    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    public static String ATTRIBUTE_KEY = "role";

    @ModelAttribute
    public void initSessionUser(HttpServletRequest request){
        threadLocal.set((User)request.getSession().getAttribute(UserController.ATTRIBUTE_KEY));
        log.warn("测试登录用户:{}",threadLocal.get());
    }

    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<RoleVO> roleVOList = roleService.condition(null);
        PageBean<RoleVO> pageBean = PageBean.initData(roleVOList.size(), roleVOList);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/condition/{pageNum}/{pageSize}")
    public AxiosResult condition(@RequestBody RoleVO roleVO, @PathVariable int pageNum, @PathVariable int pageSize){
        log.warn("参数:{}", roleVO);

        PageHelper.startPage(pageNum, pageSize);

        List<RoleVO> roleVOList = roleService.condition(roleVO);
        PageBean<RoleVO> pageBean = PageBean.initData(roleVOList.size(), roleVOList);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public AxiosResult detail(@PathVariable Long id){

        RoleVO roleVO = roleService.getById(id,true);
        return AxiosResult.success(roleVO);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public AxiosResult delete(@PathVariable Long id){

        roleService.removeById(id, threadLocal.get());

        return AxiosResult.success();
    }

    /**
     * 方法描述 : 修改操作. 传入 RoleVO, 更新 role , role-permission
     * @author : Ryze
     * @date : 2021-11-19
     * @params :
     *    @param roleVO
     * @return : com.shangma.common.http.AxiosResult
     */
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public AxiosResult update(@RequestBody RoleVO roleVO) {

        roleService.updateById(roleVO, threadLocal.get());
        //roleService.flushUpdate(roleVO, threadLocal.get());// 直接在service处理更新

        RoleVO newRoleVO = roleService.getById(roleVO.getId(), true);
        return AxiosResult.success(newRoleVO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public AxiosResult add(@RequestBody RoleVO roleVO) {
        boolean save = roleService.add(roleVO, threadLocal.get());

        if (!save)
            return AxiosResult.error(AxiosStatus.ERROR_SYSTEM_ADD);
        return AxiosResult.success();
    }

}

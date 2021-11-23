package com.shangma.controller.system;

import com.github.pagehelper.PageHelper;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.PermissionVO;
import com.shangma.entity.system.vo.RoleVO;
import com.shangma.service.system.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:24
 * @Description :
 */
@Slf4j
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    @ModelAttribute
    public void initSessionUser(HttpServletRequest request){
        threadLocal.set((User)request.getSession().getAttribute(UserController.ATTRIBUTE_KEY));
        log.warn("测试登录用户:{}",threadLocal.get());
    }

    /**
     * 方法描述:
     *  文档要求: 返回以<strong color='red'>角色(id)</strong>为唯一标识的集合.<br/>
     *  实际效果: 返回以<strong color='red'>权限(id)</strong>为唯一标识的集合.
     */
    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PermissionVO> permissionVOList = permissionService.condition(null);
        PageBean<PermissionVO> pageBean = PageBean.initData(permissionVOList.size(), permissionVOList);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/condition/{pageNum}/{pageSize}")
    public AxiosResult condition(@RequestBody PermissionVO permissionVO, @PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);

        List<PermissionVO> permissionVOList = permissionService.condition(permissionVO);
        PageBean<PermissionVO> pageBean = PageBean.initData(permissionVOList.size(), permissionVOList);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public AxiosResult detail(@PathVariable Long id){

        PermissionVO permissionVO = permissionService.getById(id);
        return AxiosResult.success(permissionVO);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public AxiosResult delete(@PathVariable Long id){

        boolean flag = permissionService.removeById(id, threadLocal.get());

        return AxiosResult.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public AxiosResult update(@RequestBody PermissionVO permissionVO) {

        permissionService.updateById(permissionVO, threadLocal.get());// todo 待做

        PermissionVO newPermissionVO = permissionService.getById(permissionVO.getId());
        return AxiosResult.success(newPermissionVO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public AxiosResult add(@RequestBody PermissionVO permissionVO) {
        boolean save = permissionService.add(permissionVO, threadLocal.get());

        if (!save)
            return AxiosResult.error(AxiosStatus.ERROR_SYSTEM_ADD);
        return AxiosResult.success();
    }


}

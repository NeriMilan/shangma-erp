package com.shangma.controller.system;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.PermissionVO;
import com.shangma.entity.system.vo.ReportVO;
import com.shangma.entity.system.vo.RoleVO;
import com.shangma.entity.system.vo.UserRolePermissionVO;
import com.shangma.service.system.PermissionService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    @RequestMapping(value="/report/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public AxiosResult report(@PathVariable int pageNum, @PathVariable int pageSize){

        PageHelper.startPage(pageNum, pageSize);

        List<UserRolePermissionVO> userRolePermissionVOList = permissionService.conditionReport(null);
        PageBean<UserRolePermissionVO> pageBean = PageBean.initData(userRolePermissionVOList.size(), userRolePermissionVOList);
        return AxiosResult.success(pageBean);
    }


    @RequestMapping(value="/conditionReport/{pageNum}/{pageSize}", method = RequestMethod.POST)
    public AxiosResult conditionReport(@RequestBody User user, @PathVariable int pageNum, @PathVariable int pageSize){

        PageHelper.startPage(pageNum, pageSize);

        List<UserRolePermissionVO> userRolePermissionVOList = permissionService.conditionReport(user);
        PageBean<UserRolePermissionVO> pageBean = PageBean.initData(userRolePermissionVOList.size(), userRolePermissionVOList);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/report/download", method = RequestMethod.GET)
    public void downloadReport(HttpServletResponse response) throws IOException {

        List<UserRolePermissionVO> userRolePermissionVOList = permissionService.conditionReport(null);

        List<ReportVO> rows = permissionService.getDownloadRows(userRolePermissionVOList);

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();

        writer.addHeaderAlias("id", "用户编号");
        writer.addHeaderAlias("loginName", "用户登录名");
        writer.addHeaderAlias("username", "用户姓名");
        writer.addHeaderAlias("departmentName", "归属部门");
        writer.addHeaderAlias("sexName", "用户性别");
        writer.addHeaderAlias("statusName", "用户状态");
        writer.addHeaderAlias("jobName", "用户职务");
        writer.addHeaderAlias("roleName", "角色");
        writer.addHeaderAlias("permissionNames", "权限名称");
        writer.addHeaderAlias("scopes", "权限范围");

// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
//out为OutputStream，需要写出到的目标流

//response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String filename = "权限报表.xls";
        response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        ServletOutputStream out=response.getOutputStream();

        writer.flush(out, true);
// 关闭writer，释放内存
        writer.close();
//此处记得关闭输出Servlet流
        IoUtil.close(out);
    }


}

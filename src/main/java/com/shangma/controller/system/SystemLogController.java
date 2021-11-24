package com.shangma.controller.system;

import com.github.pagehelper.PageHelper;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.SystemLog;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.RoleVO;
import com.shangma.entity.system.vo.SystemLogVO;
import com.shangma.service.system.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-24 10:03
 * @Description :
 */
@Slf4j
@RestController
@RequestMapping("/system/log")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    @ModelAttribute
    public void initSessionUser(HttpServletRequest request){
        threadLocal.set((User)request.getSession().getAttribute(UserController.ATTRIBUTE_KEY));
        log.warn("测试登录用户:{}",threadLocal.get());
    }

    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<SystemLog> systemLogList = systemLogService.condition(null);
        PageBean<SystemLog> pageBean = PageBean.initData(systemLogList.size(), systemLogList);
        return AxiosResult.success(pageBean);
    }


    @RequestMapping(value = "/condition/{pageNum}/{pageSize}", method = RequestMethod.POST)
    public AxiosResult condition(@RequestBody SystemLogVO systemLogVO, @PathVariable int pageNum, @PathVariable int pageSize){
        log.warn("参数:{}", systemLogVO);

        PageHelper.startPage(pageNum, pageSize);

        List<SystemLog> systemLogList = systemLogService.condition(systemLogVO);
        PageBean<SystemLog> pageBean = PageBean.initData(systemLogList.size(), systemLogList);
        return AxiosResult.success(pageBean);
    }
}

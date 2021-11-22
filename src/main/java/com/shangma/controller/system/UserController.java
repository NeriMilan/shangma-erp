package com.shangma.controller.system;

import com.github.pagehelper.PageHelper;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.User;
import com.shangma.service.system.DepartmentService;
import com.shangma.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author : Ryze
 * @create : 2021-11-17 17:09
 * @Description :
 * // todo 下拉选列表与字典表处理
 */
@Slf4j
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static String ATTRIBUTE_KEY = "user";

    @ModelAttribute
    public void initSessionUser(HttpServletRequest request) {
        threadLocal.set((User) request.getSession().getAttribute(ATTRIBUTE_KEY));
        log.warn("测试登录用户:{}", threadLocal.get());
    }

    @RequestMapping("/login/{loginName}/{password}")
    public AxiosResult login(@PathVariable String loginName, @PathVariable String password, HttpServletRequest request) {

        List<User> list = userService.listByLoginNameAndPassword(loginName, password);

        if (list != null && list.size() == 1) {
            User loginUser = list.get(0);
            request.getSession().setAttribute(ATTRIBUTE_KEY, loginUser);
            log.info("登陆后的Session中的user:{}", request.getSession().getAttribute(ATTRIBUTE_KEY));
            return AxiosResult.success(loginUser);
        }
        return AxiosResult.error(AxiosStatus.ERROR_SYSTEM_LOGIN_NOTFOUND);
    }

    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.list();
        PageBean<User> pageBean = PageBean.initData(users.size(), users);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/condition/{pageNum}/{pageSize}")
    public AxiosResult condition(@RequestBody User user, @PathVariable int pageNum, @PathVariable int pageSize) {
        log.warn("参数:{}", user);

        PageHelper.startPage(pageNum, pageSize);

        List<User> list = userService.condition(user);
        PageBean<User> pageBean = PageBean.initData(list.size(), list);
        return AxiosResult.success(pageBean);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public AxiosResult detail(@PathVariable Long id) {

        User user = userService.getById(id);
        return AxiosResult.success(user);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public AxiosResult delete(@PathVariable Long id) {
        userService.removeById(id, threadLocal.get());
        return AxiosResult.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public AxiosResult update(@RequestBody User user) {

        Long id = user.getId();
        userService.updateById(user, threadLocal.get());
        User newUser = userService.getById(id);

        return AxiosResult.success(newUser);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public AxiosResult add(@RequestBody User user) {
        boolean save = userService.add(user, threadLocal.get());

        if (!save)
            return AxiosResult.error(AxiosStatus.ERROR_SYSTEM_ADD);
        return AxiosResult.success();
    }


}

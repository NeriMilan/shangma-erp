package com.shangma.interceptor;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.User;
import com.shangma.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static com.shangma.common.http.AxiosStatus.ERROR_SYSTEM_LOGIN_NOT;

/**
 * @author : Ryze
 * @create : 2021-11-18 16:19
 * @Description : 登录校验
 */
@Slf4j
//@Component
public class LoginInterceptor implements HandlerInterceptor {

    // 造成null的原因是因为拦截器加载是在springcontext创建之前完成的. 处理方式: 注册拦截器使用 @Bean 方法
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            PrintWriter out = response.getWriter();
            JSONObject res = new JSONObject();
            AxiosResult axiosResult = AxiosResult.error(ERROR_SYSTEM_LOGIN_NOT);
            res.put("status", axiosResult.getStatus());
            res.put("message", axiosResult.getMessage());
            out.append(res.toString());

            return false;
        }

        log.info("LoginInterceptor类userService:{}",userService);
        user = userService.getById(user.getId());
        session.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

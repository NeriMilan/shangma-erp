package com.shangma.interceptor;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.entity.system.Operate;
import com.shangma.entity.system.OperateExample;
import com.shangma.entity.system.SystemLog;
import com.shangma.entity.system.User;
import com.shangma.mapper.system.OperateMapper;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.SystemLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static com.shangma.common.http.AxiosStatus.ERROR_SYSTEM_LOGIN_NOT;

/**
 * @author : Ryze
 * @create : 2021-11-17 15:18
 * @Description :
 */
@Slf4j
//@Component
public class CheckPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private OperateMapper operateMapper;

    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
        <Strong color='blue'>
        往操作表填充数据
     </Strong>
     */
    public void injectToOperate(String targetLocation){

        log.info("测试 redis:{}",stringRedisTemplate);
        log.info("permissionMapper 测试:{}",permissionMapper);
        log.info("operateMapper 测试:{}",operateMapper);
        log.info("targetLocation 测试:{}",targetLocation);

        String location = targetLocation.substring(0, targetLocation.indexOf("("));
        log.info("substring 测试:{}",location);

        for (Operate operate : operateMapper.selectList(null))
            if (operate.getLocation().equals(location))
                return;

        OperateExample operateExample = new OperateExample();
        OperateExample.Criteria operateExampleCriteria = operateExample.createCriteria();
        operateExampleCriteria.andLocationEqualTo(location.substring(0,location.indexOf("#")));
        List<Operate> temp = operateMapper.selectByExample(operateExample);

        Operate operate = new Operate();
//        operate.setPid(temp.get(0).getId());
        operate.setLocation(location);
        operateMapper.insertSelective(operate);
    }

    public void injectServletPath(HttpServletRequest request){
        String servletPath = request.getServletPath();
        log.info("测试路径:{}", servletPath);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String targetLocation = handler.toString();

        if (!targetLocation.startsWith("com.shangma."))
            return true;

        // todo 填充数据用
        injectToOperate(targetLocation);

        injectServletPath(request);


        User user = (User)request.getSession().getAttribute("user");

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        Long roleId = Long.valueOf(ops.get("shangma-erp:system:user:" + user.getId() + ":roleId"));
        List<String> operates = permissionMapper.getOperatesByRoleId(roleId);

        for (String operate : operates) {
            if (StringUtils.startsWith(targetLocation, operate)){
                return true;
            }
        }

        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        AxiosResult axiosResult = AxiosResult.error(AxiosStatus.ERROR_SYSTEM_PERMISSION);
        res.put("status", axiosResult.getStatus());
        res.put("message", axiosResult.getMessage());
        out.append(res.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String target = handler.toString();
        if (!target.startsWith("com.shangma.")){
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
            return;
        }


        User user = (User)request.getSession().getAttribute("user");
        // 按照 . 或者 # 切分字符串[\.|\#]
        String[] split = target.split("[\\\\.|\\\\#]");
        String module = split[3];

        System.out.println(target);
        String method = target.substring(0, target.indexOf('('));

        OperateExample operateExample = new OperateExample();
        OperateExample.Criteria criteria = operateExample.createCriteria();
        criteria.andLocationEqualTo(method);
        List<Operate> operates = operateMapper.selectByExample(operateExample);
        Operate operate = operates.get(0);

        log.info("测试1:{}", target);
        log.info("测试2:{}", method);

        SystemLog systemLog = new SystemLog(null, null, user.getUsername(), module, operate.getDescription(),user.getDepartmentId(),user.getIp(), user.getLoginName(), null, null);
        log.info("测试3:{}",systemLog);

        systemLogMapper.insertSelective(systemLog);

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

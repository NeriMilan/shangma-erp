package com.shangma.service.system;

import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 17:07
 * @Description :
 */
public interface UserService {

    List<User> list();

    List<User> condition(User user);

    User getById(Long id);

    boolean removeById(Long id, User master);

    boolean updateById(User user, User master);

    boolean add(User user, User master);

    List<User> listByLoginNameAndPassword(String loginName, String password);

    boolean flushUpdate(Long id, User master);

    //LoginUserVO getLoginUserByUser(User loginUser);
}

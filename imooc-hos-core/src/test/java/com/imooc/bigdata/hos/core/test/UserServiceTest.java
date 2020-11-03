package com.imooc.bigdata.hos.core.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.imooc.bigdata.hos.core.usermgr.IUserService;
import com.imooc.bigdata.hos.core.usermgr.model.SystemRole;
import com.imooc.bigdata.hos.core.usermgr.model.UserInfo;
import com.imooc.bigdata.hos.mybatis.test.BaseTest;

/**
 * Created by Lingyu on 10/01/2020.
 */
public class UserServiceTest extends BaseTest {

  @Autowired
  @Qualifier("userServiceImpl")
  IUserService userService;

  @Test
  public void addUser() {
    UserInfo userInfo = new UserInfo("Lingyu", "123456", SystemRole.ADMIN, "no desc");
    userService.addUser(userInfo);
  }

  @Test
  public void getUser() {
    UserInfo userInfo = userService.getUserInfoByName("Lingyu");
    System.out
        .println(
            userInfo.getUserId() + "|" + userInfo.getUserName() + "|" + userInfo.getPassword());
  }

  @Test
  public void deleteUser() {
    UserInfo userInfo = userService.getUserInfoByName("Lingyu");
    userService.deleteUser(userInfo.getUserId());
  }
}

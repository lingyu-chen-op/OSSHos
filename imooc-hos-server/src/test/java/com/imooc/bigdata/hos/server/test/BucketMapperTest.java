package com.imooc.bigdata.hos.server.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.imooc.bigdata.hos.common.BucketModel;
import com.imooc.bigdata.hos.core.authmgr.IAuthService;
import com.imooc.bigdata.hos.core.authmgr.model.ServiceAuth;
import com.imooc.bigdata.hos.core.usermgr.IUserService;
import com.imooc.bigdata.hos.core.usermgr.model.SystemRole;
import com.imooc.bigdata.hos.core.usermgr.model.UserInfo;
import com.imooc.bigdata.hos.mybatis.test.BaseTest;
import com.imooc.bigdata.hos.server.dao.BucketMapper;

/**
 * Created by Lingyu on 10/01/2020.
 */
public class BucketMapperTest extends BaseTest {

  @Autowired
  BucketMapper bucketMapper;
  @Autowired
  @Qualifier("authServiceImpl")
  IAuthService authService;
  @Autowired
  @Qualifier("userServiceImpl")
  IUserService userService;

  @Test
  public void addBucket() {
    BucketModel bucketModel = new BucketModel("test1", "Lingyu", "");
    bucketMapper.addBucket(bucketModel);
    UserInfo userInfo = new UserInfo("Lingyu", "123456", SystemRole.ADMIN, "");
    userService.addUser(userInfo);
    ServiceAuth serviceAuth = new ServiceAuth();
    serviceAuth.setTargetToken(userInfo.getUserId());
    serviceAuth.setBucketName(bucketModel.getBucketName());
    authService.addAuth(serviceAuth);
    BucketModel bucketModel2 = new BucketModel("test2", "Lingyu", "");
    bucketMapper.addBucket(bucketModel2);
  }

  @Test
  public void getBucket() {
    BucketModel bucketModel = bucketMapper.getBucketByName("test1");
    System.out.println(bucketModel.getBucketId() + "|" + bucketModel.getBucketName());
  }

  @Test
  public void getUserAuthorizedBuckets() {
    UserInfo userInfo = userService.getUserInfoByName("Lingyu");
    List<BucketModel> bucketModels = bucketMapper.getUserAuthorizedBuckets(userInfo.getUserId());
    bucketModels.forEach(bucketModel -> {
      System.out.println(bucketModel.getBucketId() + "|" + bucketModel.getBucketName());
    });
  }

  @Test
  public void deleteBucket() {
    UserInfo userInfo = userService.getUserInfoByName("Lingyu");
    List<BucketModel> bucketModels = bucketMapper.getUserAuthorizedBuckets(userInfo.getUserId());
    bucketModels.forEach(bucketModel -> {
      bucketMapper.deleteBucket(bucketModel.getBucketId());
    });
  }
}

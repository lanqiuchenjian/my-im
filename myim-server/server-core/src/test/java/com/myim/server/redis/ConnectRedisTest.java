package com.myim.server.redis;

import com.myim.common.constant.Constant;
import com.myim.server.BaseTest;
import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.dao.gen.domain.ImUserExample;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ConnectRedisTest extends BaseTest {
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private ImUserMapper imUserMapper;

    @Test
    public void getSetTest() {
        String test = "test";
        redisDao.setKey(test, "ab");
        Object key = redisDao.getKey(test);
        System.out.println(key);
//        redisDao.deleteKey(test);
    }


    @Test
    public void getLikeChineseTest() {
        String key = "zaddKey";
        redisDao.zddKey(key, "张三:1");
        redisDao.zddKey(key, "张四:2");
        redisDao.zddKey(key, "王三:3");
        redisDao.zddKey(key, "tom:4");
        redisDao.zddKey(key, "nike:5");

        Set<String> te = redisDao.getObscureKey(key, "t", 0, 2);
        Set<String> te1 = redisDao.getObscureKey(key, "张", 0, 2);
        Set<String> te2 = redisDao.getObscureKey(key, "张", 0, 2);
        Set<String> te3 = redisDao.getObscureKey(key, "张", 0, 2);

        System.out.println("");
    }

    @Test
    public void syncMysqlUserInfoToRedis() {
        ImUserExample imUserExample = new ImUserExample();
        imUserExample.createCriteria().andIdIsNotNull();
        List<ImUser> imUsers = imUserMapper.selectByExample(imUserExample);

        imUsers.forEach(imUser -> {
                    System.out.println(imUser);
            Boolean aBoolean = redisDao.zddKey(Constant.USERNAME_ID, imUser.getLoginName() + ":" + String.valueOf(imUser.getId()));
            System.out.println(aBoolean);
                }
        );
    }
}

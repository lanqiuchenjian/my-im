package com.myim.server.dao.datasource;

import com.myim.server.dao.BaseTest;
import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectMysqlTest extends BaseTest {
    @Autowired
    private ImUserMapper imUserMapper;
    @Test
    public void dataSourceConntect() {
        ImUser imUser = imUserMapper.selectByPrimaryKey(1L);
        System.out.println("xxx");
        System.out.println(imUser);
    }
}

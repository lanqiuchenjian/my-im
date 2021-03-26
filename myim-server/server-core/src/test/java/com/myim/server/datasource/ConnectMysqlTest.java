package com.myim.server.datasource;

import com.myim.server.BaseTest;
import com.myim.server.dao.ext.mapper.ImMessageExtMapper;
import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConnectMysqlTest extends BaseTest {
    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImMessageExtMapper imMessageExtMapper;

    @Test
    public void dataSourceConnect() {
        ImUser imUser = imUserMapper.selectByPrimaryKey(1L);
        System.out.println("xxx");
        System.out.println(imUser);
    }

    @Test
    public void getCount() {
        int count = imMessageExtMapper.getCount(50L);
        System.out.println(count);
    }


    @Test
    public void getHisMessage() {
        List<ImMessage> hisMesByPage = imMessageExtMapper.getHisMesByPageWithIdDesc(100000L, 21L, 50L);
        System.out.println(hisMesByPage);
    }
}

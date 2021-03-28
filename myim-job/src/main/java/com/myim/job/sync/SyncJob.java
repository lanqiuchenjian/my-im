package com.myim.job.sync;

import com.myim.common.constant.Constant;
import com.myim.server.dao.ext.mapper.ImUserExtMapper;
import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author cj
 */
@Component
public class SyncJob {
    @Autowired
    private ImUserExtMapper imUserExtMapper;

    @Autowired
    private RedisDao redisDao;

    @PostConstruct
    public void syncUserInfoDbToCache() {
        Long id = 0L;
        Long size = 30L;

        while (id != null)
            id = doSyncUserInfoDbToCache(id, size);
    }

    private Long doSyncUserInfoDbToCache(Long id, Long size) {
        List<ImUser> userLists = imUserExtMapper.getUserLists(id, size);
        userLists.forEach(imUser -> redisDao.zddKey(Constant.USERNAME_ID, imUser.getLoginName() + ":" + String.valueOf(imUser.getId())));
        if (userLists.size() < size)
            return null;
        else
            return userLists.get(size.intValue() - 1).getId();
    }
}

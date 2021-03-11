package com.myim.server.api.service.impl;

import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.service.UserService;
import com.myim.server.common.BeanCommon;
import com.myim.server.dao.gen.domain.ImUser;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import com.myim.server.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ImUserMapper imUserMapper;

    @Override
    public UserRegisterRespDto register(UserRegisterReqDto userRegisterReqDto) {
        ImUser imUser = new ImUser();
        BeanCommon.copyFromTo(userRegisterReqDto, imUser, false);
        imUserMapper.insertSelective(imUser);
        UserRegisterRespDto userRegisterRespDto = new UserRegisterRespDto();
        return BaseResponse.success(userRegisterRespDto);
    }

    @Override
    public void login(Message message) {

    }
}

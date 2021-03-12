package com.myim.server.api.service.impl;

import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserLoginRespDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.service.UserService;
import com.myim.server.common.BeanCommon;
import com.myim.server.constant.Constant;
import com.myim.server.dao.gen.domain.*;
import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.exception.user.UserNotExistException;
import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImUserSingleCategoryMapper imUserSingleCategoryMapper;

    @Autowired
    private ImUserSingleRelationMapper imUserSingleRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRegisterRespDto register(UserRegisterReqDto userRegisterReqDto) {
        ImUser imUser = new ImUser();
        BeanCommon.copyFromTo(userRegisterReqDto, imUser, false);
        imUserMapper.insertSelective(imUser);
        //用户注册时候，默认新增我的好友分组
        ImUserSingleCategory imUserSingleCategory = new ImUserSingleCategory();
        imUserSingleCategory.setImUserId(imUser.getId());
        imUserSingleCategory.setCategoryName(Constant.MY_FRIEND);
        imUserSingleCategory.setCount(0L);
        imUserSingleCategoryMapper.insertSelective(imUserSingleCategory);

        UserRegisterRespDto userRegisterRespDto = new UserRegisterRespDto();
        return BaseResponse.success(userRegisterRespDto);
    }

    @Override
    public UserLoginRespDto login(UserLoginReqDto userLoginReqDto) {
        ImUser imUser = new ImUser();
        BeanCommon.copyFromTo(userLoginReqDto, imUser, false);

        ImUserExample imUserExample = new ImUserExample();
        imUserExample.createCriteria()
                .andLoginNameEqualTo(imUser.getLoginName());

        List<ImUser> imUsers = imUserMapper.selectByExample(imUserExample);

        if (imUsers == null || imUsers.size() == 0) {
            throw new UserNotExistException(CodeMsgEnum.USER_OR_PASSWORD_ERROR);
        }

        if (!imUsers.get(0).getLoginPassword().equals(userLoginReqDto.getLoginPassword())) {
            throw new UserNotExistException(CodeMsgEnum.USER_OR_PASSWORD_ERROR);
        }

        UserLoginRespDto userLoginRespDto = new UserLoginRespDto();
        return BaseResponse.success(userLoginRespDto);
    }

    /**************************************************************************************************************************/
    @Override
    public ApplyFriendRespBo applyFriend(ApplyFriendReqBo applyFriendBo) {
        ImUser imUser = imUserMapper.selectByPrimaryKey(applyFriendBo.getFromImUserId());

        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        imUserSingleCategoryExample.createCriteria()
                .andImUserIdEqualTo(imUser.getId())
                .andCategoryNameEqualTo(Constant.MY_FRIEND);
        List<ImUserSingleCategory> imUserSingleCategories = imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);


        ImUserSingleRelation imUserSingleRelation = new ImUserSingleRelation();
        imUserSingleRelation.setImUserId(applyFriendBo.getToImUserId());
        imUserSingleRelation.setImUserSingleCategoryId(imUserSingleCategories.get(0).getId());
        imUserSingleRelationMapper.insertSelective(imUserSingleRelation);

        return BaseResponse.success(new ApplyFriendRespBo());
    }
}

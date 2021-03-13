package com.myim.server.api.service.impl;

import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserLoginRespDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.dto.resp.base.ListBaseResponse;
import com.myim.server.api.service.UserService;
import com.myim.server.common.BeanCommon;
import com.myim.server.constant.Constant;
import com.myim.server.dao.gen.domain.*;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.exception.user.UserNotExistException;
import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.req.FriendInfoReqBo;
import com.myim.server.message.bo.req.FriendListInfoReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import com.myim.server.message.bo.resp.FriendInfoRespBo;
import com.myim.server.message.bo.resp.FriendListInfoRespBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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
    @Transactional(rollbackFor = Exception.class)
    public ApplyFriendRespBo applyFriend(ApplyFriendReqBo applyFriendBo) {
        Long fromImUserId = applyFriendBo.getFromImUserId();
        Long toImUserId = applyFriendBo.getToImUserId();

        //互相添加好友
        doApply(fromImUserId, toImUserId);
        doApply(toImUserId, fromImUserId);

        return BaseResponse.success(new ApplyFriendRespBo());
    }

    @Override
    public FriendInfoRespBo friendInfo(FriendInfoReqBo friendInfoReqBo) {
        ImUser imUser = imUserMapper.selectByPrimaryKey(friendInfoReqBo.getToImUserId());
        FriendInfoRespBo friendInfoRespBo = new FriendInfoRespBo();

        BeanCommon.copyFromTo(imUser, friendInfoRespBo, false);

        friendInfoRespBo.setCategoryName(friendInfoReqBo.getCategoryName());
        return BaseResponse.success(friendInfoRespBo);
    }

    @Override
    public FriendListInfoRespBo friendListInfo(FriendListInfoReqBo friendListInfoReqBo) {
        FriendListInfoRespBo friendListInfoRespBo = new FriendListInfoRespBo();
        List<FriendInfoRespBo> list = new ArrayList<>();
        //获取组内所有的好友
        ImUserSingleRelationExample imUserSingleRelationExample = new ImUserSingleRelationExample();
        imUserSingleRelationExample.createCriteria().andImUserSingleCategoryIdEqualTo(friendListInfoReqBo.getImUserCategoryId());
        List<ImUserSingleRelation> imUserSingleRelations = imUserSingleRelationMapper.selectByExample(imUserSingleRelationExample);

        imUserSingleRelations.forEach(imUserSingleRelation -> {
            FriendInfoRespBo friendInfoRespBo = new FriendInfoRespBo();
            ImUser imUser = imUserMapper.selectByPrimaryKey(imUserSingleRelation.getImUserId());
            BeanCommon.copyFromTo(imUser, friendInfoRespBo, false);
            list.add(friendInfoRespBo);
        });
        friendListInfoRespBo.setData(list);
        return ListBaseResponse.success(friendListInfoRespBo);
    }

    private void doApply(Long fromImUserId, Long toImUserId) {
        Long imUserId = imUserMapper.selectByPrimaryKey(fromImUserId).getId();

        String myFriend = Constant.MY_FRIEND;

        List<ImUserSingleCategory> imUserSingleCategories = getImUserSingleCategoryByCategoryName(imUserId, myFriend);

        ImUserSingleRelation imUserSingleRelation = new ImUserSingleRelation();
        imUserSingleRelation.setImUserId(toImUserId);
        imUserSingleRelation.setImUserSingleCategoryId(imUserSingleCategories.get(0).getId());

        imUserSingleRelationMapper.insertSelective(imUserSingleRelation);
    }

    private List<ImUserSingleCategory> getImUserSingleCategoryByCategoryName(Long imUserId, String myFriend) {
        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        ImUserSingleCategoryExample.Criteria criteria = imUserSingleCategoryExample.createCriteria()
                .andImUserIdEqualTo(imUserId);

        if (myFriend != null && !myFriend.trim().equals(""))
            criteria.andCategoryNameEqualTo(myFriend);

        return imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);
    }

    private List<ImUserSingleCategory> getImUserSingleCategoryByCategoryId(Long imUserId, Long categoryId) {
        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        ImUserSingleCategoryExample.Criteria criteria = imUserSingleCategoryExample.createCriteria()
                .andImUserIdEqualTo(imUserId);

        if (categoryId != null)
            criteria.andIdEqualTo(categoryId);

        return imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);
    }
}

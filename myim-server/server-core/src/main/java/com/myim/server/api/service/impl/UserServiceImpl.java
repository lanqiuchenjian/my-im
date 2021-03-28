package com.myim.server.api.service.impl;

import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserLoginRespDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.common.basepojo.BaseResponse;
import com.myim.common.basepojo.ListBaseResponse;
import com.myim.server.api.service.UserService;
import com.myim.server.common.BeanCommon;
import com.myim.common.constant.Constant;
import com.myim.server.dao.gen.domain.*;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.user.UserExistedException;
import com.myim.common.exception.user.UserNotExistException;
import com.myim.server.message.bo.req.user.ApplyFriendReqBo;
import com.myim.server.message.bo.req.user.FriendInfoReqBo;
import com.myim.server.message.bo.req.user.FriendListInfoReqBo;
import com.myim.server.message.bo.req.user.SearchFriendListInfoReqBo;
import com.myim.server.message.bo.resp.user.ApplyFriendRespBo;
import com.myim.server.message.bo.resp.user.FriendInfoRespBo;
import com.myim.server.message.bo.resp.user.FriendListInfoRespBo;
import com.myim.server.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImUserSingleCategoryMapper imUserSingleCategoryMapper;

    @Autowired
    private ImUserSingleRelationMapper imUserSingleRelationMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRegisterRespDto register(UserRegisterReqDto userRegisterReqDto) {
        ImUser imUser = new ImUser();
        BeanCommon.copyFromTo(userRegisterReqDto, imUser, false);

        try{
            imUserMapper.insertSelective(imUser);
        }catch (DuplicateKeyException e){
            throw new UserExistedException(CodeMsgEnum.USER_EXISTED_ERROR, e);
        }
        //用户注册时候，默认新增我的好友分组
        ImUserSingleCategory imUserSingleCategory = new ImUserSingleCategory();
        imUserSingleCategory.setImUserId(imUser.getId());
        imUserSingleCategory.setCategoryName(Constant.MY_FRIEND);
        imUserSingleCategory.setCount(0L);
        imUserSingleCategoryMapper.insertSelective(imUserSingleCategory);

        //新增用户存入redis中
        redisDao.zddKey(Constant.USERNAME_ID, imUser.getLoginName() + ":" + String.valueOf(imUser.getId()));

        UserRegisterRespDto userRegisterRespDto = new UserRegisterRespDto();
        userRegisterRespDto.setRegisterImUserId(imUser.getId());
        userRegisterRespDto.setSingleCategoryId(imUserSingleCategory.getId());
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

        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        imUserSingleCategoryExample.createCriteria().andImUserIdEqualTo(imUsers.get(0).getId());

        List<ImUserSingleCategory> imUserSingleCategories = imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);
        List<Long> ids = imUserSingleCategories.stream().map(ImUserSingleCategory::getId).collect(Collectors.toList());

        UserLoginRespDto userLoginRespDto = new UserLoginRespDto();
        userLoginRespDto.setRegisterImUserId(imUsers.get(0).getId());
        userLoginRespDto.setSingleCategoryIdList(ids);
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

        ApplyFriendRespBo applyFriendRespBo = new ApplyFriendRespBo();
        applyFriendRespBo.setServiceType("applySingleFriend");
        return BaseResponse.success(applyFriendRespBo);
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
            friendInfoRespBo.setImUserId(imUser.getId());
            list.add(friendInfoRespBo);
        });
        friendListInfoRespBo.setData(list);
        friendListInfoRespBo.setServiceType("friendListInfo");
        return ListBaseResponse.success(friendListInfoRespBo);
    }

    @Override
    public FriendListInfoRespBo searchFriendListInfo(SearchFriendListInfoReqBo searchFriendListInfoReqBo) {
        FriendListInfoRespBo friendListInfoRespBo = new FriendListInfoRespBo();
        friendListInfoRespBo.setServiceType("searchFriendListInfo");
        List<FriendInfoRespBo> list = new ArrayList<>();

        Set<String> userSet = redisDao.getObscureKey(Constant.USERNAME_ID, searchFriendListInfoReqBo.getLoginNamePre(),
                searchFriendListInfoReqBo.getOffset(), searchFriendListInfoReqBo.getSize());

        List<Long> ids = userSet.stream()
                .map(u -> Long.valueOf(u.split(":")[1])).collect(Collectors.toList());

        if (ids.size() == 0)
            return ListBaseResponse.success(friendListInfoRespBo);

        ImUserExample imUserExample = new ImUserExample();
        imUserExample.createCriteria().andIdIn(ids);
        List<ImUser> imUsers = imUserMapper.selectByExample(imUserExample);

        imUsers.forEach(imUser -> {
            FriendInfoRespBo friendInfoRespBo = new FriendInfoRespBo();
            BeanCommon.copyFromTo(imUser, friendInfoRespBo, false);
            friendInfoRespBo.setImUserId(imUser.getId());
            list.add(friendInfoRespBo);
        });

        friendListInfoRespBo.setData(list);
        return ListBaseResponse.success(friendListInfoRespBo);
    }

    @Override
    public ImUserSingleRelation getRelationByImUserIAndCategoryId(Long toImUserId, Long categoryId) {
        ImUserSingleRelationExample imUserSingleRelationExample = new ImUserSingleRelationExample();
        imUserSingleRelationExample.createCriteria()
                .andImUserSingleCategoryIdEqualTo(categoryId)
                .andImUserIdEqualTo(toImUserId);
        List<ImUserSingleRelation> imUserSingleRelations = imUserSingleRelationMapper.selectByExample(imUserSingleRelationExample);
        return imUserSingleRelations.get(0);
    }

    @Override
    public ImUserSingleRelation getRelationByFromAndToId(Long fromImUserId, Long toImUserId) {
        ImUser imUser = imUserMapper.selectByPrimaryKey(fromImUserId);

        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        imUserSingleCategoryExample.createCriteria().andImUserIdEqualTo(imUser.getId());
        List<ImUserSingleCategory> imUserSingleCategories = imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);

        for (ImUserSingleCategory i : imUserSingleCategories) {
            ImUserSingleRelationExample imUserSingleRelationExample = new ImUserSingleRelationExample();
            imUserSingleRelationExample.createCriteria().andImUserSingleCategoryIdEqualTo(i.getId());
            List<ImUserSingleRelation> imUserSingleRelations = imUserSingleRelationMapper.selectByExample(imUserSingleRelationExample);

            Optional<ImUserSingleRelation> any = imUserSingleRelations.stream().filter(iusr -> Objects.equals(iusr.getImUserId(), toImUserId)).findAny();

            if (any.isPresent())
                return any.get();
        }
        throw new UserNotExistException(CodeMsgEnum.USER_EXISTED_ERROR);
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

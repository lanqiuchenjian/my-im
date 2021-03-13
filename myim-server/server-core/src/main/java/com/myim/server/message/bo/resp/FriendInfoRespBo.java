package com.myim.server.message.bo.resp;

import com.myim.server.api.dto.resp.base.BaseResponse;
import lombok.Data;

@Data
public class FriendInfoRespBo extends BaseResponse{
    private Long imUserId;

    private String userName;

    private String nickName;

    private Integer phone;

    private String photo;

    private String loginName;

    private String loginPassword;

    private Integer userStatus;

    private String serviceAliasName;

    /***************************************/
    private String categoryName;


}

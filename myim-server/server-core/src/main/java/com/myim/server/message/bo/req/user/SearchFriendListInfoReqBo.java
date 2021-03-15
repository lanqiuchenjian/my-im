package com.myim.server.message.bo.req.user;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class SearchFriendListInfoReqBo extends BaseRequestBo {
    private String loginNamePre;
    private Integer offset;
    private Integer size;
}

package com.myim.router.service;

import com.myim.router.dto.request.ImRouterReqDto;
import com.myim.router.dto.response.ImRouterRespDto;

/**
 * @author cj
 */
public interface ImRouterService {
    ImRouterRespDto doRouter(ImRouterReqDto imRouterReqDto);
}

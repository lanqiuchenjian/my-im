package com.myim.cluster.service.impl;

import com.myim.cluster.dto.request.ImServerReqDto;
import com.myim.cluster.dto.response.ImServerRespDto;
import com.myim.cluster.service.ImServerService;
import com.myim.router.dto.request.ImRouterReqDto;
import com.myim.router.dto.response.ImRouterRespDto;
import com.myim.router.service.ImRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cj
 */
@Component
public class ImServerServiceImpl implements ImServerService {
    @Autowired
    private ImRouterService imRouterService;
    @Override
    public ImServerRespDto getTargetServerInfo(ImServerReqDto imServerReqDto) {
        ImServerRespDto imServerRespDto = new ImServerRespDto();
        ImRouterReqDto imRouterReqDto = new ImRouterReqDto();
        ImRouterRespDto imRouterRespDto = imRouterService.doRouter(imRouterReqDto);

        imServerRespDto.setHost(imRouterRespDto.getHost());
        imServerRespDto.setPort(imRouterRespDto.getPort());

        return imServerRespDto;
    }
}

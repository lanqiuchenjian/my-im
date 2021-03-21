package com.myim.cluster.service;

import com.myim.cluster.dto.request.ImServerReqDto;
import com.myim.cluster.dto.response.ImServerRespDto;

public interface ImServerService {
	ImServerRespDto getTargetServerInfo(ImServerReqDto imServerReqDto);
}

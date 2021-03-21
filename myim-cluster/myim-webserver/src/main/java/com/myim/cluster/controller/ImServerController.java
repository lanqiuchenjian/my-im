package com.myim.cluster.controller;

import com.myim.cluster.dto.request.ImServerReqDto;
import com.myim.cluster.dto.response.ImServerRespDto;
import com.myim.cluster.service.ImServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/im/server")
public class ImServerController {
	@Autowired
	private ImServerService imServerService;

	@PostMapping(value = "/target/info")
	public ResponseEntity<ImServerRespDto> getTargetServerInfo(@RequestBody ImServerReqDto imServerReqDto)  {
		ImServerRespDto targetServerInfo = imServerService.getTargetServerInfo(imServerReqDto);
		return ResponseEntity.ok(targetServerInfo);
	}
}

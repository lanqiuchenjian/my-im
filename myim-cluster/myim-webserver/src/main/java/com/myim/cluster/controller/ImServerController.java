package com.myim.cluster.controller;

import com.myim.cluster.dto.request.ImServerReqDto;
import com.myim.cluster.dto.request.MulReqDto;
import com.myim.cluster.dto.response.ImServerRespDto;
import com.myim.cluster.service.ImServerService;
import com.myim.common.basepojo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/im/server")
public class ImServerController {
	@Autowired
	private ImServerService imServerService;

	@PostMapping(value = "/target/info")
	public ResponseEntity<ImServerRespDto> getTargetServerInfo(@RequestBody ImServerReqDto imServerReqDto)  {
		ImServerRespDto targetServerInfo = imServerService.getTargetServerInfo(imServerReqDto);
		return ResponseEntity.ok(BaseResponse.success(targetServerInfo));
	}

	@PostMapping(value = "/target/mul")
	public ResponseEntity<ImServerRespDto> getMul (MulReqDto mulReqDto, MultipartFile[] applyFiles)  {
		System.out.println(mulReqDto);
		System.out.println(applyFiles);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/target/one")
	public OneR one(@RequestBody One one)  {
		System.out.println("one===" + one);
		OneR oneR = new OneR();

		List<String> res = new ArrayList<>();
		res.add("success");
		res.add("fail");
		oneR.setStatus(res);
		return oneR;
	}

	@PostMapping(value = "/target/two")
	public TwoR two(@RequestBody Two two)  {
		System.out.println("two===" + two);
		TwoR twoR = new TwoR();
		List<String> list = new ArrayList<>();
		list.add("res1");
		list.add("res2");
		twoR.setApiRes(list);
		return twoR;
	}
}

package com.myim.server.api.controller;

import com.jcraft.jsch.SftpException;
import com.myim.server.api.dto.req.MulReqDto;
import com.myim.server.common.FileUploadBySftpUtils;
import com.myim.server.message.push.DefaultMessagePusher;
import com.myim.server.model.Message;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


@RestController
@RequestMapping("/api/file")
public class FileController {
	private static final String dir = "/data/pic/";

	@PostMapping(value = "/target/mul")
	public ResponseEntity<Map<String, String>> getMul (MulReqDto mulReqDto, MultipartFile[] applyFiles)  {
		Map<String, String> hashMap = new HashMap<>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month = cal.get(Calendar.MONTH) + 1;//获取月份
		int day = cal.get(Calendar.DATE);//获取日

		String date = "" + year + month + day;
		String realDir = dir + date;
		Arrays.stream(applyFiles).forEach(file -> {
			String fileName = UUID.randomUUID().toString().replaceAll("-", "");
			String suffix = file.getOriginalFilename().split("\\.")[file.getOriginalFilename().split("\\.").length - 1];
			try {
				String sftpFileName = fileName + "." + suffix;
				FileUploadBySftpUtils.upload(realDir, sftpFileName, file.getBytes());
				hashMap.put(file.getOriginalFilename(), "http://47.110.41.97:1081/pic/" + date + "/" + sftpFileName);
			} catch (SftpException | IOException ignored) {
			}
		});
		return ResponseEntity.ok(hashMap);
	}
}

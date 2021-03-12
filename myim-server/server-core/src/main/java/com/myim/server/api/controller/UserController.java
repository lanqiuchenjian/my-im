/*
 * Copyright 2013-2019 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.myim.server.api.controller;

import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserLoginRespDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.server.api.service.UserService;
import com.myim.server.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity<UserRegisterRespDto> register(UserRegisterReqDto userRegisterReqDto)  {
		UserRegisterRespDto userRegisterRespDto = userService.register(userRegisterReqDto);
		return ResponseEntity.ok(userRegisterRespDto);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UserLoginRespDto> login(@RequestBody(required = false) UserLoginReqDto userLoginReqDto)  {
		UserLoginRespDto userLoginRespDto = userService.login(userLoginReqDto);
		return ResponseEntity.ok(userLoginRespDto);
	}
}

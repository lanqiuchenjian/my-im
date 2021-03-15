package com.myim.server.dao.controller;

import com.google.gson.Gson;
import com.myim.server.api.controller.UserController;
import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.service.UserService;
import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.dao.BaseTest;
import com.myim.server.exception.user.UserNotExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@AutoConfigureMockMvc
//@TestPropertySource(properties={
//        "spring.autoconfigure.exclude=" +
//                "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration," +
//                "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration," +
//                "com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure," +
//                "org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration"})
public class ControllerTest extends BaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void before(){
        System.out.println("你好");
        UserController userController = new UserController();
        MockitoAnnotations.initMocks(userService);
        ReflectionTestUtils.setField(userController, "userService", userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        Mockito.when(userService.login(Mockito.any())).thenThrow(new UserNotExistException(CodeMsgEnum.USER_OR_PASSWORD_ERROR));
//        Mockito.when(userService.login(Mockito.any())).thenReturn(BaseResponse.success(new UserLoginRespDto()));

        //调用原始方法
//        Mockito.when(userService.createProcess(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenCallRealMethod();
    }

    @Test
    public void start() throws Exception {
        UserLoginReqDto userLoginReqDto = new UserLoginReqDto();

        userLoginReqDto.setLoginName("xx");
        userLoginReqDto.setLoginPassword("pp");
        String s = new Gson().toJson(userLoginReqDto);
        s = s.replaceAll("loginName", "loginXX");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .content(s).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");

        resultActions.andDo(print());
    }
}

package com.myim.server.api.controller.global;

import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
   //处理自定义的异常
   @ExceptionHandler(BaseException.class)
   @ResponseBody
   public Object customHandler(BaseException e){
      e.printStackTrace();
      return ResponseEntity.ok(BaseResponse.fail(new BaseResponse(), e.getCodeMsgEnum()));
   }

   //其他未处理的异常
   @ExceptionHandler(Exception.class)
   @ResponseBody
   public Object exceptionHandler(Exception e){
      e.printStackTrace();
      return ResponseEntity.ok(BaseResponse.fail(new BaseResponse(), CodeMsgEnum.SYSTEM_ERROR));
   }
}

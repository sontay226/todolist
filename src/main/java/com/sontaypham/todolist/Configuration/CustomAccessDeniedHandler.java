package com.sontaypham.todolist.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sontaypham.todolist.DTO.Response.ApiResponse;
import com.sontaypham.todolist.Exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {
    ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
    response.setStatus(errorCode.getHttpStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    ApiResponse<?> apiResponse =
        ApiResponse.builder().status(errorCode.getCode()).message(errorCode.getMessage()).build();
    ObjectMapper objectMapper = new ObjectMapper();
    response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    response.flushBuffer();
  }
}

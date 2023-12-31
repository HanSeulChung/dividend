package com.dayone.exception;

import com.dayone.exception.impl.UserNotFoundException;
import io.lettuce.core.RedisConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AbstractException.class)
    protected ResponseEntity<?> handleCustomException(AbstractException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getStatusCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(e.getStatusCode()));
    }
    @ExceptionHandler(RedisConnectionException.class)
    protected ResponseEntity<?> handleRedisConnectionException(RedisConnectionException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(500)
                .message(e.getMessage() + " Redis 서버가 연결되지 않았습니다.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(500));
    }
//    @ExceptionHandler(UserNotFoundException.class)
//    protected ResponseEntity<?> handleUsernameNotFoundException(UserNotFoundException e) {
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .code(400)
//                .message(e.getMessage())
//                .build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(400));
//    }


    // 여기서 handle을 하고싶음. 터미널 log는 이렇게 찍히지만 POST MAN에서 동작확인 시에는 handle되지 않음
    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<?> handleUserNotFoundException(UsernameNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(500)
                .message(e.getMessage() + " 사용자가 존재하지 않습니다. 올바른 로그인 후 접근 권한이 필요합니다.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(500));
    }

}

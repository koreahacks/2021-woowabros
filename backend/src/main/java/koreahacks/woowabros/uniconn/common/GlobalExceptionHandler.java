package koreahacks.woowabros.uniconn.common;

import koreahacks.woowabros.uniconn.exception.AlreadySelectedException;
import koreahacks.woowabros.uniconn.exception.AlreadyVerifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AlreadySelectedException.class, AlreadyVerifiedException.class, IllegalArgumentException.class})
    public Mono<ResponseEntity<String>> handleDefault(RuntimeException e) {
        return Mono.just(ResponseEntity.badRequest().body(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleUnknown(Exception e) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
    }
}

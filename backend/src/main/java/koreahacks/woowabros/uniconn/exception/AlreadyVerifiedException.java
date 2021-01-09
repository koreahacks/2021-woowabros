package koreahacks.woowabros.uniconn.exception;

public class AlreadyVerifiedException extends RuntimeException {
    public AlreadyVerifiedException() {
        super("이미 인증을 받으셨습니다");
    }
}

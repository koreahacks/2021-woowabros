package koreahacks.woowabros.uniconn.exception;

public class AlreadySelectedException extends RuntimeException {
    public AlreadySelectedException() {
        super("이미 채택된 답변입니다");
    }
}

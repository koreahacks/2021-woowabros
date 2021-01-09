package koreahacks.woowabros.uniconn.member.application;

public interface EmailService {
    void sendAuthEmail(String targetEmail, String authCode);
}

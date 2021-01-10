package koreahacks.woowabros.uniconn.common;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDAuthCodeGenerator implements AuthCodeGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
    }
}

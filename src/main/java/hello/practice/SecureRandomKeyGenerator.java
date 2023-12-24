package hello.practice;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureRandomKeyGenerator {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256비트 키
        random.nextBytes(keyBytes);
        String secureKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("JWT Secret Key: " + secureKey);
    }
}

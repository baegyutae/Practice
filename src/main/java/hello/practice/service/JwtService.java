package hello.practice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String SECRET_KEY = "yourSecretKey";

    public String generateToken(String user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // 토근 만료 시간 (ex.1시간)
        Date exp = new Date(expMillis);

        return Jwts.builder()
            .setSubject(user)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }
}

package com.cmsk.wxplatform.common.auth;

import com.cmsk.wxplatform.common.utils.JwtUtil;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.cmsk.wxplatform.common.utils.RequestContextUtil.getHttpRequest;

/**
 * @author zhanglgstart
 * @create 2021-03-08 10:39
 */
@Component
public class AuthContext {

    public static final String ISS = "iss";//appkey
    public static final String EXP = "exp";//过期时间

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String USER_ID_JWT_KEY = "userId";
    private static final String TOKEN_JWT_KEY = "token";
    private static final String MEMBER_ID_JWT_KEY = "memberId";
    private static final String BIG_VIP_KEY_JWT_KEY = "key";
    private final JwtConfig jwtConfig;

    public AuthContext(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    private String getToken() {
        HttpServletRequest request = getHttpRequest();
        if (request == null) {
            return Strings.EMPTY;
        }

        return request.getHeader(AUTHORIZATION_HEADER_KEY);
    }

    void verifyToken() throws UnauthorizedException {
        String token = getToken();

        if (Strings.isBlank(token)) {
            throw new UnauthorizedException("Please login");
        }

        if (!JwtUtil.verify(token, jwtConfig.getMiniBeSecret())) {
            throw new UnauthorizedException("Illegal token");
        }
    }

    @SneakyThrows
    public String createToken(String appKey, Long expirationTime) {
        Map<String, Object> tokenValues = new HashMap<>();
        tokenValues.put(ISS, appKey);
        tokenValues.put(EXP, expirationTime);

        return JwtUtil.createJwtToken(tokenValues, jwtConfig.getMiniBeSecret());
    }
}

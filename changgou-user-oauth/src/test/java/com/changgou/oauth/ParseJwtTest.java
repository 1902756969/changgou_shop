package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {

    @Test
    public void parseJwt(){
        //基于公钥去解析jwt
        String jwt ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU2OTc4MDYyOSwiYXV0aG9yaXRpZXMiOlsiYWNjb3VudGFudCIsInVzZXIiLCJzYWxlc21hbiJdLCJqdGkiOiIwNGVkODYyOC03NTBhLTRjYzktYWY5My01ZDliNTJhMDU3NzUiLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInVzZXJuYW1lIjoiaGVpbWEifQ.U4triwcNsNhYFiHEp1-kdyNJhNAzJZQkJqRFfaF-eJYKEl9hhxkvdrXh0R9dbsjiNVAtKMP-Khvz63QM5tmzxFKF1YoPR9vDb1xAqhsYhFLpwkp61ADYVeJ4AHvI7HHyNPuzXsnTd_y47vcL56OHFBXDouQePLrtqt-24sKdFHgAHwnGS6S-N20jhYJIkdeUsfWbJbO5b4_UZegkAoR4GN6UGJSn7WqOPxryQXCJ_XBEo0P-duhaWpkvr_YKrc14HeXPnY4A4bFUZniqRt__VVC8ihKvsA2ZBNKZ8BpI8g4gqI3I674MRktZIv7qzr46fv9YrBkNFq73LiZ78lPTlA";

        String publicKey ="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";

        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));

        String claims = token.getClaims();
        System.out.println(claims);
    }
}

package com.changgou.oauth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class CreateJWTTest {

    @Test
    public void createJWT(){
        //基于私钥生成jwt
        //1. 创建一个秘钥工厂
        //1: 指定私钥的位置
        ClassPathResource classPathResource = new ClassPathResource("changgou.jks");
        //2: 指定秘钥库的密码
        String keyPass = "changgou";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,keyPass.toCharArray());

        //2. 基于工厂获取私钥
        String alias = "changgou";
        String password = "changgou";
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
        //将当前的私钥转换为rsa私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //3.生成jwt
        Map<String,String> map = new HashMap();
        map.put("company","heima");
        map.put("address","beijing");

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));
        String jwtEncoded = jwt.getEncoded();
        System.out.println(jwtEncoded);
    }
}

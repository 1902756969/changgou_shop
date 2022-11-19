package com.changgou.seckill.web.aspect;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) //不仅保存到class文件中,并且jvm加载class之后,该注解仍然存在
public @interface AccessLimit {
}

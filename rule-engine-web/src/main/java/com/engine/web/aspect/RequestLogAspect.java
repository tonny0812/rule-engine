/**
 * Copyright @2020 dingqianwen
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.engine.web.aspect;

import com.alibaba.fastjson.JSON;
import com.engine.web.util.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈请求日志〉
 *
 * @author 丁乾文
 * @create 2019/8/13
 * @since 1.0.0
 */
@Component
@Aspect
@Slf4j
public class RequestLogAspect {

    /**
     * 打印请求日志
     *
     * @param joinPoint 连接点
     * @return 被代理类方法执行结果
     * @throws Throwable .
     */
    @Around("execution(* com.engine.web.controller..*.*(..))&&!execution(* com.engine.web.controller.exception.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder("\n");
        long start = System.currentTimeMillis();
        try {
            sb.append("┏━━━━━━━━请求日志━━━━━━━━\n");
            sb.append("┣ 地址: ").append(HttpServletUtils.getRequest().getRequestURL()).append("\n");
            sb.append("┣ 参数: ").append(JSON.toJSONString(argsExcludeClass(joinPoint.getArgs()))).append("\n");
            Object proceed = joinPoint.proceed();
            sb.append("┣ 结果: ").append(JSON.toJSONString(proceed)).append("\n");
            return proceed;
        } catch (Throwable throwable) {
            sb.append("┣ 异常: ").append(throwable).append("\n");
            throw throwable;
        } finally {
            sb.append("┣ 时间: ").append(System.currentTimeMillis() - start).append("ms\n");
            sb.append("┗━━━━━━━━━━━━━━━━━━━━━━━");
            log.info("{}", sb);
        }
    }

    /**
     * 参数过滤调一部分类,否则引起问题
     *
     * @param args 参数
     * @return Object[]
     */
    private static Object[] argsExcludeClass(Object[] args) {
        return Stream.of(args)
                .filter(f -> !(f instanceof HttpServletResponse))
                .filter(f -> !(f instanceof HttpServletRequest))
                .filter(f -> !(f instanceof MultipartFile))
                .filter(f -> !(f instanceof Exception)).toArray();
    }
}

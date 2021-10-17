package org.rex.aop.sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"org.rex.aop.sample"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopApplicationConfig {
}

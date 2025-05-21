package org.example.aspect;

public class LoggingAspect {
    public void logBeforeMethod() {
        System.out.println("[系统日志] 开始执行登录验证...");
    }

    public void logAfterMethod() {
        System.out.println("[系统日志] 登录验证流程结束\n");
    }
}
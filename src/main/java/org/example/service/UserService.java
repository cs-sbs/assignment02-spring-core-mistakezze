package org.example.service;

import java.util.Set;

public class UserService {
    // 模拟数据库中的用户信息
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "123456";
    private static final String DEFAULT_ROLE = "管理员";

    // 被锁定的账户集合
    private Set<String> lockedAccounts;

    public void setLockedAccounts(Set<String> lockedAccounts) {
        this.lockedAccounts = lockedAccounts;
    }

    /**
     * 用户登录认证
     * @return 认证结果码：
     * 0-成功，1-密码错误，2-账户锁定，3-账户不存在
     */
    public int login(String username, String password) {
        // 检查账户是否存在
        if (!VALID_USERNAME.equals(username)) {
            return 3; // 账户不存在
        }

        // 检查账户是否被锁定
        if (lockedAccounts.contains(username)) {
            return 2; // 账户被锁定
        }

        // 验证密码
        if (!VALID_PASSWORD.equals(password)) {
            return 1; // 密码错误
        }

        return 0; // 认证成功
    }

    /**
     * 获取用户权限级别
     */
    public String getUserRole() {
        return DEFAULT_ROLE;
    }
}
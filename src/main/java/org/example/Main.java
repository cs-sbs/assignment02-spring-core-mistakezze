package org.example;

import org.example.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== 用户认证系统 ==========");
            System.out.print("请输入用户名（输入 'exit' 退出）: ");
            String username = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(username)) {
                System.out.println("感谢使用，再见！");
                break;
            }

            System.out.print("请输入密码: ");
            String password = scanner.nextLine().trim();

            int result = userService.login(username, password);
            displayLoginResult(userService, username, result, scanner);
        }

        scanner.close();
        context.close();
    }

    private static void displayLoginResult(UserService service, String user, int result, Scanner scanner) {
        switch (result) {
            case 0:
                System.out.printf("\n✅ 用户登录成功！\n");
                // 新增代码验证步骤
                System.out.print("请输入身份验证代码（输入代码显示权限）: ");
                String code = scanner.nextLine().trim();
                if ("admin".equals(code)) {
                    System.out.printf("欢迎，%s！您当前的权限级别为：%s\n", user, service.getUserRole());
                } else {
                    System.out.println("⚠️ 代码无效，权限信息未显示");
                }
                break;
            case 1:
                System.out.println("\n❌ 用户名或密码错误，请重新输入！\n💡 如果您忘记密码，请点击“找回密码”");
                break;
            case 2:
                System.out.println("\n🔒 您的账户已被锁定，无法登录！\n⚠️ 原因：多次登录失败，请联系管理员解锁");
                break;
            case 3:
                System.out.println("\n❓ 用户名不存在，请检查后重新输入！\n💡 如果您尚未注册，请点击“注册新账户”");
                break;
            default:
                System.out.println("\n⚠️ 未知错误，请联系系统管理员");
        }
    }
}
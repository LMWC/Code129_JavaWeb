package exercise.work03_正则表达式.work03_2;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        System.out.println("请输入一个手机号码,必须以数字1开头,第二位是：3,5,7,8中的一位,剩下的全部是数字");
        Scanner sc = new Scanner(System.in);
        String mobile = sc.nextLine();
        String regex = "1[3578]\\d{9}";
        System.out.println(mobile.matches(regex));
    }
}

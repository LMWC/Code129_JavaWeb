package exercise.work03_正则表达式.work03_1;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        System.out.println("请输入一个QQ号码,5--12位长度,全部是数字,首位不能是0");
        Scanner sc = new Scanner(System.in);
        String qq = sc.nextLine();
        String regex = "[1-9]\\d{4,11}";
        System.out.println(qq.matches(regex));
    }
}

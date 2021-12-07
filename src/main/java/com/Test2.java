package com;

public class Test2 {
    public static void main(String[] args) {
        if (check1(false) || check2(true)) {
            System.out.println("||");
        }else {
            System.out.println("dsdsd");
        }

        if (check1(false) && check2(true)) {
            System.out.println("检测 &&");
        }
    }

    public static boolean check1(boolean flag) {
        System.out.println("check1" +flag);
        return flag;
    }

    public static boolean check2(boolean flag) {
        System.out.println("check2" +flag);
        return flag;
    }
}

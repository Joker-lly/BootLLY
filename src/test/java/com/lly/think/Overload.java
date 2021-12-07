package com.lly.think;

import com.lly.util.page.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Overload {
    public static void sayHello(Object arg) {
        System.out.println("hello object" + arg);
    }

    /*public static void sayHello(int arg) {
        System.out.println("hello int" + arg);
    }*/

    public static void sayHello(long arg) {
        System.out.println("hello long" + arg);
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character" + arg);
    }

//    public static void sayHello(char arg) {
//        System.out.println("hello char" + arg);
//    }

    public static void sayHello(char... arg) {
        System.out.println("hello char..." + arg);
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable" + arg);
    }

    public <T> T say(T arg){
        return arg;
    }


    public static void main(String[] args) {
        List<? extends Fruit> list = Arrays.asList(new Apple());

        Apple apple = new Apple();
        //list.add(apple);
        Overload overload = new Overload();
        System.out.println(overload.say("123"));
        sayHello('a');

        Random random = new Random(47);
        System.out.println(random.nextInt());
    }

}

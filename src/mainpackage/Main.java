package mainpackage;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        
        Object[] arr = new Object[rand.nextInt(10)];
        //Object[] arr = new Object[0];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Object();
        }
        
        Function function = new Function() {
            public void func() {
                System.out.println("function performed");
            }
        };
        
        syncDo(function, arr);
        System.out.println("\nsize of array: " + arr.length);
    }
    
    private static void syncDo(Function fun, Object[] synchedOn) {
        if (synchedOn.length > 1) {
            synchronized (synchedOn[0]) {
                System.out.println("now synched on: " + synchedOn[0]);
                syncDo(fun, Arrays.copyOfRange(synchedOn, 1, synchedOn.length));
            }
            
            System.out.println("lost sync on:   " + synchedOn[0]);
        } else if (synchedOn.length == 1) {
            synchronized (synchedOn[0]) {
                System.out.println("now synched on: " + synchedOn[0]);
                fun.func(); // see Function.java
            }
            
            System.out.println("lost sync on:   " + synchedOn[0]);
        } else {
            fun.func();
        }
    }
}
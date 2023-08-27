package ru.geekbrains.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Program {

    public static void main(String[] args) {
        HashMap<String, String> hashMap1 = new HashMap<>(4);
        String oldValue = hashMap1.put("+79001234567", "AAAAA");
        oldValue = hashMap1.put("+79001234560", "MMMMM");
        oldValue = hashMap1.put("+79001234560", "BBB");
        oldValue = hashMap1.put("+79001234562", "CCC1");
        oldValue = hashMap1.put("+79001234572", "CCC2");
        oldValue = hashMap1.put("+79001234582", "CCC3");
        oldValue = hashMap1.put("+79001234592", "CCC4");
        oldValue = hashMap1.put("+79001234662", "CCC5");
        oldValue = hashMap1.put("+79001234762", "CCC6");
        oldValue = hashMap1.put("+79001234862", "CCC7");
        oldValue = hashMap1.put("+79001234962", "CCC8");

//
//        String res1 = hashMap1.get("+79001234592");
//        String res2 = hashMap1.get("+79221234592");
//
//
//        String res3 = hashMap1.remove("+79001234592");
//        String res4 = hashMap1.remove("+79001234862");
//        String res5 =hashMap1.remove("+79001234862");


        for (HashMap.Entity e : hashMap1 ){
            System.out.println(e.key + " " + e.value);
        }


    }

}

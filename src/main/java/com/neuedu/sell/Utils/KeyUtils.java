package com.neuedu.sell.Utils;

import java.util.Random;

/**
 * 随机数6位+时间
 */
public class KeyUtils {
    public static synchronized String generateUniqueKey(){
        Random random = new Random();
        int number = random.nextInt(90000)+10000;
        return String.valueOf(System.currentTimeMillis()+number);
    }
}

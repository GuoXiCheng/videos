package com.videos.Utils;

import java.util.Random;
import java.util.UUID;

public class KeyUtil {
    //生成唯一的主键
    //格式：时间+随机数
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString().replace("-","");
        return uuidStr;
    }

    public static void main(String[] args){
        System.out.println(getUUID());
    }

}


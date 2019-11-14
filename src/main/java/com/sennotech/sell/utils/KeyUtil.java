package com.sennotech.sell.utils;
/*
 *   @author 吴少航
 *   @date 2019/10/15-15:03
 */

import java.util.Random;

public class KeyUtil {
    /*
     * 生成唯一组件
     * 格式：时间+随机数
     */

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}

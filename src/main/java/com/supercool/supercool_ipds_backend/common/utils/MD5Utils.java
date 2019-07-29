package com.supercool.supercool_ipds_backend.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param input 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String MD5(String input){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(input.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }
}
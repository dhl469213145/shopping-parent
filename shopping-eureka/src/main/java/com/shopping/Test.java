package com.shopping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(isChinese("中文123AGBjkd8还有993"));

        String testStr = "1！啊绿1色空3间asdf  12大";
        String testStr2 = "中文123AGBjkd8还有993";
        String testStr3 = "222这是一个例子GGbb888";
//        System.out.println("count="+getHZCount(testStr));
//        System.out.println("count="+getNumCount(testStr));
//        System.out.println("count="+getEnCount(testStr));

        System.out.println("HZcount="+getHZCount(testStr2));
        System.out.println("SZcount="+getNumCount(testStr2));
        System.out.println("Encount="+getEnCount(testStr2));


        System.out.println("HZcount="+getHZCount(testStr3));
        System.out.println("SZcount="+getNumCount(testStr3));
        System.out.println("Encount="+getEnCount(testStr3));
    }

    private static int getHZCount(String str) {
        String regex = "[\\u4e00-\\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count=0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static int getNumCount(String str) {
        String regex = "[0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count=0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static int getEnCount(String str) {
        String regex = "[[a-zA-Z]]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count=0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}

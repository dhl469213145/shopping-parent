package com.shopping;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {
    public static void main( String args[] ) throws ScriptException {
        // 按指定模式在字符串查找
        String line = "(500CNY+200CNY)*4|(100USD+20USD)*2";
        String pattern = "((([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]))CNY|(([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]))USD)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        List<String> replaceStrs = new ArrayList<>();
        while (m.find( )) {
//            System.out.println("0Found value: " + m.group() );
            String temStr = m.group(0);
//            newStr.replace(temStr, );
            replaceStrs.add(temStr);
//            System.out.println(temStr.replace("USD", ""));
        }

        BigDecimal arg = new BigDecimal("6.82432");

        for(int i = 0; i < replaceStrs.size(); i++) {
            String str = replaceStrs.get(i);
            // 处理美元字符转换
            if(str.contains("USD")) {
                String reStr = str.replace("USD", "");
                BigDecimal temp = new BigDecimal(reStr);

                line = line.replace(str, temp.multiply(arg).toString());
            }
            // 处理人民币字符转换
            if(str.contains("CNY")) {
                line = line.replace(str, str.replace("CNY", ""));
            }
        }

        line = line.replace("|", "+");
        System.out.println(line);

        // 运算算式
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object eval = engine.eval(line);
        BigDecimal result = new BigDecimal(eval.toString());
        System.out.println(result.setScale(0, BigDecimal.ROUND_HALF_DOWN));
    }
}

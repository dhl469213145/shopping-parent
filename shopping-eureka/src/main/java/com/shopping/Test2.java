package com.shopping;

import java.io.*;

public class Test2 {

    public static void main(String[] args) {
        OutputStream os = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            String testWord = "我是一列竖着的测试字符串!";
            File filePath = new File("C:\\test\\");
            if(!filePath.exists()) {
                filePath.mkdir();
            }
            File newFile = new File(filePath, "text.txt");
            newFile.createNewFile();
            os = new FileOutputStream(newFile);
            writer = new OutputStreamWriter(os, "UTF8");
            bw = new BufferedWriter(writer);

            for(int i = 0; i < testWord.length(); i++) {
                bw.write(testWord.charAt(i));
                bw.newLine();
            }
            bw.flush();
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (os!=null){
                    os.close();
                }
                if(writer!=null){
                    writer.close();
                }
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

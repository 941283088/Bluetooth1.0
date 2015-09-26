package com.common;

import android.content.Context;
import android.os.Environment;
import org.apache.http.util.EncodingUtils;

import java.io.*;

/**
 * Created by TR on 2015/9/24.
 */
public  class FileCtl{
    public static void writeFileSdcardFile(String fileName,String write_str)
    {
        try{
            if(write_str.length()<2)
                return;
            //判断是否有sdcard
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            }else
            {
                File saveFile = new File("/sdcard/"+fileName);
                try {
                    FileOutputStream outStream = new FileOutputStream(saveFile);

                    outStream.write(write_str.getBytes());

                    outStream.close();
                }catch(Exception e)
                {e.printStackTrace();}
            }
        }
       catch(Exception e){
        e.printStackTrace();
        }
    }

    public static void method2(String fileName,String content){
        try{
            //打开一个写文件器构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer=new FileWriter("/sdcard/"+fileName,true);
            writer.write(content);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String readFileSdcardFile(String fileName){
        String res="";
        try{
           FileInputStream fin = new FileInputStream(fileName);

           int length = fin.available();
           byte [] buffer = new byte[length];
           fin.read(buffer);
           res = EncodingUtils.getString(buffer, "UTF-8");
           fin.close();
            }
        catch(Exception e){
        e.printStackTrace();
        }
        return res;
    }
}

package com.common;

import org.apache.http.util.EncodingUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by TR on 2015/9/24.
 */
public  class FileCtl {
    public static void writeFileSdcardFile(String fileName,String write_str)
    {
        try{
        FileOutputStream fout = new FileOutputStream(fileName);
        byte [] bytes = write_str.getBytes();

        fout.write(bytes);
       fout.close();
        }
       catch(Exception e){
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

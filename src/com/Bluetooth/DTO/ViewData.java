package com.Bluetooth.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TR on 2015/9/24.
 */
public class ViewData {
    public int value;
    public Date date;
    public String ext;


    public String toCVS()
    {
        String ans="";
        ans += (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(this.date);
        ans +=",";
        ans += value;
        ans += "\n";
        ans += "\n";
        return ans;
    }
}

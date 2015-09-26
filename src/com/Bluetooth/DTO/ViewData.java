package com.Bluetooth.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TR on 2015/9/24.
 */
public class ViewData {
    public Integer value;
    public Date date;
    public String ext;

    public ViewData()
    {
        value = 0;
        date = new Date();
        ext="";
    }
    public String toCVS()
    {
        String ans="";
        ans += (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(this.date);
        ans +=",";
        ans += "'" + value.toString() + "',";
        ans += "'" + ext + "'";
        ans += "\r\n";
        return ans;
    }
}

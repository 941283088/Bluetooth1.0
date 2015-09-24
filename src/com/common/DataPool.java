package com.common;

import com.Bluetooth.DTO.ViewData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Created by TR on 2015/9/24.
 */

public class DataPool {
    private static Queue<ViewData> QVD;
    public static boolean add(ViewData data)
    {
        if(QVD == null)
        {
            QVD = new LinkedList<ViewData>();
        }
       return  QVD.offer(data);
    }
    public static List<ViewData> get(int n)
    {
        List<ViewData> lvd = new ArrayList<ViewData>();
        if(QVD == null)
           return lvd;
        while (n!=0)
        {
            lvd.add(QVD.poll());
            n--;
        }
        return lvd;
    }
    public static String getJsonList(int n)
    {
        String ans="";

        return ans;
    }
    public static String getCVS(int n)
    {
        String ans="";
        List<ViewData> tl= get(n);
        for(ViewData t : tl)
        {
            ans += t.toCVS();
        }
        return  ans;
    }
}

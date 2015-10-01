package com.common;

/**
 * Created by TR on 2015/9/24.
 */
public class WriteThread extends Thread  {
    private  int step;
    private  int len;
    //执行频率，单位秒
    public WriteThread(int st,int length)
    {
        step = st > 0 ? st*1000:1000;
        len = length > 0 ? length:60;
    }
    public void run()
    {
        while(true) {
            try {
                Thread.sleep(step);
                //FileCtl.writeFileSdcardFile("test.txt", DataPool.getCVS(len));
                FileCtl.writeFileSdcardFile2("Bluetooth.txt",DataPool.getCVS(len));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

     }
}

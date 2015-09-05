package com.Bluetooth.base;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import android.app.Activity;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothClass.Device;
import android.bluetooth.BluetoothClass.Service;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothServerSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by TR on 2015/9/5.
 */
public class BluetoothTools{
    private static BluetoothAdapter mBluetoothAdapter;
    public static void initBluetooth()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
                //表明此手机不支持蓝牙
                return;
        }
        if(!mBluetoothAdapter.isEnabled()){ //蓝牙未开启，则开启蓝牙
                  mBluetoothAdapter.enable();
        }


    }

    public static Set<BluetoothDevice> getDeviceList(){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            return pairedDevices;
        }
        return null;
    }

    public static BluetoothDevice findDevicesByName(String name){
        Set<BluetoothDevice> devicesList = getDeviceList();
        if (devicesList == null) return null;
        for (BluetoothDevice devices : devicesList){
            Log.i("blueList",devices.getName()+" adress："+devices.getAddress());
            if(devices.getName().equals(name))
            {
                return devices;
            }

        }
        return null;
    }
    //UUID可以看做一个端口号
    private static final UUID MY_UUID =  UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    //像一个服务器一样时刻监听是否有连接建立
    private class AcceptThread extends Thread{
        private BluetoothServerSocket serverSocket;

        public AcceptThread(BluetoothDevice dev){
            ParcelUuid[] uuids = dev.getUuids();
            ;
            BluetoothServerSocket temp = null;
            try {
                temp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(
                        "test1", MY_UUID);

            } catch (IOException e) {
                Log.e("app", "listen() failed", e);
            }
            serverSocket = temp;
        }

        public void run(){
            BluetoothSocket socket=null;
            while(true){
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    Log.e("app", "accept() failed", e);
                    break;
                }
            }
            if(socket!=null){
                //此时可以新建一个数据交换线程，把此socket传进去
                ConnectedThread con = new ConnectedThread(socket);
                con.run();
            }
        }

        //取消监听
        public void cancel(){
            try {
                serverSocket.close();
            } catch (IOException e) {
                Log.e("app",  "close() of server failed", e);
            }
        }

    }


    //建立连接后，进行数据通信的线程
    private class ConnectedThread extends Thread{
        private BluetoothSocket socket;
        private InputStream inStream;
        private OutputStream outStream;

        public ConnectedThread(BluetoothSocket socket){

            this.socket = socket;
            try {
                //获得输入输出流
                inStream = socket.getInputStream();
                outStream = socket.getOutputStream();
            } catch (IOException e) {
                Log.e("app", "temp sockets not created", e);
            }
        }

        public void run(){
            byte[] buff = new byte[1024];
            int len=0;
            //读数据需不断监听，写不需要
            while(true){
                try {
                    len = inStream.read(buff);
                    //把读取到的数据发送给UI进行显示
                    Log.i("data:",buff.toString());

                } catch (IOException e) {
                    Log.e("app", "disconnected", e);

                    start();    //重新启动服务器
                    break;
                }
            }
        }


        public void write(byte[] buffer) {
            try {
                outStream.write(buffer);

                // Share the sent message back to the UI Activity

            } catch (IOException e) {
                Log.e("app", "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("app", "close() of connect socket failed", e);
            }
        }
    };

};



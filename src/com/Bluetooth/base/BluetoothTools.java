package com.Bluetooth.base;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
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

} ;
package com.Bluetooth.base;
import java.util.Iterator;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Created by TR on 2015/9/5.
 */
public class BluetoothTools {
    public static void initBluetooth()
    {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//直接打开系统的蓝牙设置面板
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//直接打开蓝牙
        adapter.enable();
    }
}

package com.Bluetooth.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.Bluetooth.base.BluetoothTools;
import android.util.Log;



/**
 * Created by TR on 2015/9/5.
 */
public class MainActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Log.i("dd","affair");
        BluetoothTools.initBluetooth();
        BluetoothTools.findDevicesByName("ddd");


    }
}

package com.example.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            //表明此手机不支持蓝牙
            return;
        }
        if(!mBluetoothAdapter.isEnabled()){ //蓝牙未开启，则开启蓝牙
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_ENABLE_BT){
            if(requestCode == RESULT_OK){
                //蓝牙已经开启
            }
        }
    }

}


//......



package com.example.aidldemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * aidl的服务端
 */
public class MainActivity extends AppCompatActivity {

    private static final String ACTION_RESULT = "com.example.aidldemo_IRemoteService_start";


    private TextView tv_result;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);

        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, new IntentFilter(ACTION_RESULT));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != myBroadcastReceiver)
            unregisterReceiver(myBroadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_RESULT.equals(intent.getAction())) {
                tv_result.setText(intent.getStringExtra("name") + "  启动了...");
            }
        }
    }
}

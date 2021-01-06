package com.jinasoft.study03_ksj.Broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;

import com.jinasoft.study03_ksj.R;

public class LocalBroad extends AppCompatActivity {

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broad);
        mReceiver = new BroadMain();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(BroadMain.MY_ACTION);
        registerReceiver(mReceiver, filter); // 리시버를 인텐트 필터와 함께 등록

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver); // 해제시 이걸로 필요함
    }

    public void sendMyBroadcast(View view) {
        Intent intent = new Intent(BroadMain.MY_ACTION);
        sendBroadcast(intent);
    }
}
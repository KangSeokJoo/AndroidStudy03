package com.jinasoft.study03_ksj.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

public class BroadMain extends BroadcastReceiver {

    public  static final String MY_ACTION =
            "com.jinasoft.study03_ksj.action.ACTION_Broadmain";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){ //ACTION_POWER_CONNECTED는 각 방송 정보는 인텐트로 전달된다 . 인텐트 필터를 등록을 해야함함
            Toast.makeText(context, "전원 연결 됨", Toast.LENGTH_SHORT).show();
        }else if(MY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "이 방송은 나만의 방송", Toast.LENGTH_SHORT).show();
        }else if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
            Toast.makeText(context, "배터리가득", Toast.LENGTH_SHORT).show();
        }else if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            Toast.makeText(context, "배터리없음", Toast.LENGTH_SHORT).show();
        }else if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())){
            Toast.makeText(context, "배터리확인", Toast.LENGTH_SHORT).show();
        }else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())){
            Toast.makeText(context, "전원 연결 안됨", Toast.LENGTH_SHORT).show();
        }

        // 이부분은 배터리 확인 해보고 싶어서 만듬
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
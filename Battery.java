package com.sys;
import java.io.File;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

public class Battery extends org.qtproject.qt5.android.bindings.QtActivity
{
    private static int sState;
    // 获取电池电量
    public static String getBatteryStatus(Context context)
    {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        // 这里的null可以实现一个回调监听函数 用于接受广播 这样就不用主动去获取了
        // 我就没有研究怎么去实现java回调C++代码了 有童鞋知道的话 愿不吝赐教
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        // 获取当前电量
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        // 获取充电状态
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        switch(status)
        {
        // 这里判断的是放电状态 不知道为什么没有充电状态无法获得
        case BatteryManager.BATTERY_STATUS_DISCHARGING:
            // 没有充电
            sState = 1;
            break;
        case BatteryManager.BATTERY_STATUS_CHARGING:
            // 正在充电
            sState = 2;
            break;
        case BatteryManager.BATTERY_STATUS_FULL:
            // 充满
            sState = 3;
            break;
        }
        // 计算出电量百分比
        float value = ((float)level / (float)scale) * 100;
        return value + "|" + sState;
    }
}



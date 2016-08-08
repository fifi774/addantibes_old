package com.addantibes.addantibes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by fifi on 30/07/16.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceIntent = new Intent("MyAlarmService");
            context.startService(serviceIntent);
        }
    }
}

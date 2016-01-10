package com.atmb.ggvchen.autoap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ggvch on 2015/10/18.
 */
public class WifiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("action","" + intent.getAction());
        if(intent.getAction() == "android.intent.action.ACTION_POWER_CONNECTED"){
            WifiApAdmin wifiApAdmin = new WifiApAdmin(context);
            wifiApAdmin.setMobileDataStatus(true);
            wifiApAdmin.startWifiAp("AndroidAP", "eebbkggv");
        }else if(intent.getAction() == "android.intent.action.ACTION_POWER_DISCONNECTED"){
            WifiApAdmin wifiApAdmin = new WifiApAdmin(context);
            wifiApAdmin.closeWifiAp();
            wifiApAdmin.setMobileDataStatus(false);
            boolean status = wifiApAdmin.getMobileDataStatus();
        }else if(intent.getAction() == "android.intent.action.BOOT_COMPLETED"){

        }
    }

}

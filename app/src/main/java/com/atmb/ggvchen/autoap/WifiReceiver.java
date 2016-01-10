package com.atmb.ggvchen.autoap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by ggvch on 2015/10/18.
 */
public class WifiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AutoAP", Activity.MODE_PRIVATE);
        WifiApAdmin wifiApAdmin = new WifiApAdmin(context);
        sharedPreferences.edit().putBoolean("mobileDataStatus",wifiApAdmin.getMobileDataStatus());
        sharedPreferences.edit().putBoolean("wifiStatus",wifiApAdmin.getWifiStatus());
        Log.e("action","" + intent.getAction());
        if(intent.getAction() == "android.intent.action.ACTION_POWER_CONNECTED"){
            if(sharedPreferences.getBoolean("openAPOnPowerConnected", false)) {
                wifiApAdmin.setMobileDataStatus(true);
                wifiApAdmin.startWifiAp("AndroidAP", "eebbkggv");
            }
        }else if(intent.getAction() == "android.intent.action.ACTION_POWER_DISCONNECTED"){
            if(sharedPreferences.getBoolean("closeAPOnPowerDisconnected", false)) {
                wifiApAdmin.closeWifiAp();
                wifiApAdmin.setMobileDataStatus(sharedPreferences.getBoolean("mobileDataStatus",false));
                wifiApAdmin.setWifiStatus(sharedPreferences.getBoolean("wifiStatus",false));
            }
        }else if(intent.getAction() == "android.intent.action.BOOT_COMPLETED"){

        }else if(intent.getAction() == "android.intent.action.SCREEN_OFF"){
            if(sharedPreferences.getBoolean("closeDataOnScreemOff", false)) {
                wifiApAdmin.setMobileDataStatus(false);
            }
        }else if(intent.getAction() == "android.intent.action.SCREEN_ON"){
            if(sharedPreferences.getBoolean("openDataOnScreemOn", false)) {
                wifiApAdmin.setMobileDataStatus(true);
            }
        }
    }

}

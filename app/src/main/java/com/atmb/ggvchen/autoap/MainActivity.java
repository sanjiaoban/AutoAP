package com.atmb.ggvchen.autoap;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Property;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    private WifiApAdmin wifiApAdmin;
    private Button open;
    private Button close;
    private CheckBox closeDataOnScreemOff;
    private CheckBox openDataOnScreemOn;
    private CheckBox closeAPOnPowerDisconnected;
    private CheckBox openAPOnPowerConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        open=(Button)findViewById(R.id.button);
        close=(Button)findViewById(R.id.button2);
        closeDataOnScreemOff = (CheckBox)findViewById(R.id.checkBox1);
        openDataOnScreemOn = (CheckBox)findViewById(R.id.checkBox2);
        closeAPOnPowerDisconnected = (CheckBox)findViewById(R.id.checkBox4);
        openAPOnPowerConnected = (CheckBox)findViewById(R.id.checkBox3);
        final SharedPreferences sharedPreferences = getSharedPreferences("AutoAP", Activity.MODE_PRIVATE);
        closeDataOnScreemOff.setChecked(sharedPreferences.getBoolean("closeDataOnScreemOff",false));
        openDataOnScreemOn.setChecked(sharedPreferences.getBoolean("openDataOnScreemOn",false));
        closeAPOnPowerDisconnected.setChecked(sharedPreferences.getBoolean("closeAPOnPowerDisconnected",false));
        openAPOnPowerConnected.setChecked(sharedPreferences.getBoolean("openAPOnPowerConnected",false));
        wifiApAdmin = new WifiApAdmin(this);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiApAdmin.startWifiAp("AndroidAP", "eebbkggv");
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiApAdmin.closeWifiAp();
            }
        });
        closeDataOnScreemOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("closeDataOnScreemOff", closeDataOnScreemOff.isChecked()).commit();
            }
        });
        openDataOnScreemOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("openDataOnScreemOn", openDataOnScreemOn.isChecked()).commit();
            }
        });
        closeAPOnPowerDisconnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("closeAPOnPowerDisconnected", closeAPOnPowerDisconnected.isChecked()).commit();
            }
        });
        openAPOnPowerConnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("openAPOnPowerConnected", openAPOnPowerConnected.isChecked()).commit();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

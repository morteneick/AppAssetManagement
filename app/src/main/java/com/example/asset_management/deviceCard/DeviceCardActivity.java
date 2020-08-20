package com.example.asset_management.deviceCard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.asset_management.R;

import java.io.IOException;

public class DeviceCardActivity extends AppCompatActivity {
    public boolean onOffSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_card);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Switch onOffEditSwitch = (Switch) findViewById(R.id.onOffEditSwitch);

        String test = "";
        try {
            test = getSwitch();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        final SwitchEditable switchEditable = gson.fromJson(test, SwitchEditable.class);

        onOffSwitch = switchEditable.isEnabled();
        onOffEditSwitch.setChecked(onOffSwitch);

        onOffEditSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                switchEditable.setEnabled(true);
                createSwitch(switchEditable);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            } else {
                switchEditable.setEnabled(false);
                createSwitch(switchEditable);
                finish();
                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
            }
        }
    });
    }

    public Device getDevice(){
        Intent intent = getIntent();
        return (Device)intent.getSerializableExtra("Device");
    };

    public boolean createSwitch(Object object){
        JsonHandler.createJsonFromObject(object,"Switch.json",this);
        return onOffSwitch;
    };

    public String getSwitch() throws IOException {
        return JsonHandler.getDeviceListString(this, "Switch.json");
    };

    public boolean isClicked(){
        return onOffSwitch;
    }


}
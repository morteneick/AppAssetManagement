package com.example.asset_management.deviceCard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.example.asset_management.recycleView.Device;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.asset_management.R;

public class DeviceCardActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        Device device = (Device)intent.getSerializableExtra("Device");

        EditText editInventoryNumber = findViewById(R.id.editInventoryNumber);
//        editInventoryNumber.setFocusable(false);
//        editInventoryNumber.setEnabled(false);
//        editInventoryNumber.setCursorVisible(false);
//        editInventoryNumber.setKeyListener(null);


//        editInventoryNumber.setText("device.getInventoryNumber()");


    }

    public Device getDevice(){
        Intent intent = getIntent();
        return (Device)intent.getSerializableExtra("Device");
    }

}
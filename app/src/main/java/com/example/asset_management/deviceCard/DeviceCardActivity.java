package com.example.asset_management.deviceCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.asset_management.R;

import java.io.File;
import java.io.IOException;
/**
 * DeviceCardActivity
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class DeviceCardActivity extends AppCompatActivity {
    public boolean onOffSwitch = false;
    public String fileName = "Switch.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_card);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_device, R.id.navigation_reservation, R.id.navigation_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        File file = this.getFileStreamPath(fileName);

        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),fileName);
            SwitchEditable switchEditable = new SwitchEditable(onOffSwitch);
            createSwitch(switchEditable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_devicecard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SwitchEditable switchEditable = new SwitchEditable(true);
            createSwitch(switchEditable);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            return true;
        }
        if (id == R.id.action_history) {
        Intent intent = new Intent (this, DeviceCardOldVersionsActivity.class);
        startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Device getDevice(){
        Intent intent = getIntent();
        return (Device)intent.getSerializableExtra("Device");
    }

    /**
     * creates an json from the given object
     * @param object SwitchEditable button true or false, if the card is editable
     * @return returns the onOffSwitch variable
     */
    public boolean createSwitch(Object object){
        JsonHandler.createJsonFromObject(object,"Switch.json",this);
        return onOffSwitch;
    }

    public String getSwitch() throws IOException {
        return JsonHandler.getDeviceListString(this, "Switch.json");
    }

    public boolean isClicked(){
        String isEditable = "";
        try {
            isEditable = getSwitch();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        final SwitchEditable switchEditable = gson.fromJson(isEditable, SwitchEditable.class);
        return switchEditable.isEnabled();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();
    }

    /**
     * restarts the activity with the saved intent
     */
    public void refreshUI() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
package com.example.asset_management.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.LoginActivity;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity;
import com.example.asset_management.recycleViewDeviceList.FilterDeviceListActivity;
import com.example.asset_management.recycleViewUserList.UserRecycleActivity;
import com.example.asset_management.settings.SettingsActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class NotificationMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().commit();

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        ArrayList<String> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listOptions);

        list.add("Anstehende TÜV Prüfungen");
        list.add("Anstehende UVV Prüfungen");
        list.add("Anstehende Instandhaltungstermine");
        list.add("Relevante Termine");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Connection connection = new Connection();
                ArrayList<Device> filteredList = new ArrayList<>();
                Intent intent = new Intent(NotificationMenuActivity.this, DeviceRecycleActivity.class);
                switch(position){
                    case 0:
                        connection.getTuev(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList("TuevList.json",
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("filteredList", (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 1:
                        connection.getUvv(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList("UvvList.json",
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("filteredList", (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 2:
                        connection.getMaintenance(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList("MaintenanceList.json",
                                    getApplicationContext());


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("filteredList", (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 3:
                        UserInfo userInfo = new UserInfo();
                        userInfo.setWorkerId(2);
                        connection.getBooking(getApplicationContext(), userInfo);
                        try {
                            filteredList = JsonHandler.getDeviceList("BookingList.json",
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("filteredList", (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        });
    }
}
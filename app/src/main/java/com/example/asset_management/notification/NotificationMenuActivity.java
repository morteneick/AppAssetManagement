package com.example.asset_management.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class NotificationMenuActivity extends AppCompatActivity {
    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().commit();

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        connection.getTuev(getApplicationContext());
        connection.getUvv(getApplicationContext());
        connection.getMaintenance(getApplicationContext());
        //TODO User
        UserInfo userInfo = new UserInfo();
        userInfo.setWorkerId(2);
        connection.getBooking(getApplicationContext(), userInfo);

        ArrayList<String> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listOptions);

        list.add(getString(R.string.notification0));
        list.add(getString(R.string.notification1));
        list.add(getString(R.string.notification2));
        list.add(getString(R.string.notification3));

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filteredListName = getString(R.string.filteredList);
                ArrayList<Device> filteredList = new ArrayList<>();
                Intent intent = new Intent(NotificationMenuActivity.this,
                        DeviceRecycleActivity.class);
                switch(position){
                    case 0:
                        connection.getTuev(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList(
                                    getString(R.string.tuevNameJSON),
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(filteredListName, (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 1:
                        connection.getUvv(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList(getString(R.string.uvvNameJSON),
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(filteredListName, (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 2:
                        connection.getMaintenance(getApplicationContext());
                        try {
                            filteredList = JsonHandler.getDeviceList(
                                    getString(R.string.maintenanceNameJSON),
                                    getApplicationContext());


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(filteredListName, (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    case 3:
                        UserInfo userInfo = new UserInfo();
                        userInfo.setWorkerId(2);
                        connection.getBooking(getApplicationContext(), userInfo);
                        try {
                            filteredList = JsonHandler.getDeviceList(getString
                                    (R.string.bookingNameJSON),
                                    getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(filteredListName, (Serializable) filteredList);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        });
    }
}
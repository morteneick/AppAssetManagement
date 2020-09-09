package com.example.asset_management.deviceCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.recycleView.Device;

import java.util.ArrayList;

public class DeviceCardOldVersionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_card_old_versions);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        ArrayList<String> list = new ArrayList<>();

        ListView listView = findViewById(R.id.listOldDevice);
        DeviceCardActivity activity = new DeviceCardActivity();


        list.add("12.01.2020");
        list.add("11.01.2020");
        list.add("02.01.2020");
        list.add("01.01.2020");
        list.add("19.12.2019");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
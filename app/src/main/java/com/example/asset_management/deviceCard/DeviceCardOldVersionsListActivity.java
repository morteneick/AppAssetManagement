package com.example.asset_management.deviceCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asset_management.R;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DeviceCardOldVersionsListActivity extends AppCompatActivity {
    ArrayList<Device> devices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_card_list_old_versions);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        ArrayList<String> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listOldDevice);

        try {
            devices = JsonHandler.getDeviceList("DeviceOldVersion.json", this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for(Device d : devices){
            list.add(format.format(d.getLastChange()));
        }

        if(list.size() == 0){
            list.add("Keine alten Versionen gefunden");
        } else {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(DeviceCardOldVersionsListActivity.this,
                            DeviceCardActivity.class);
                    intent.putExtra("Device", devices.get(position));
                    intent.putExtra("isOldVersion", true);
                    startActivity(intent);
                }
            });
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);


    }
}
package com.example.asset_management.recycleViewDeviceList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DeviceHistoryActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */
//TODO after deleting one device, delete it in the history too
public class DeviceHistoryActivity extends AppCompatActivity implements DeviceAdapter.OnNoteListener {
    private RecyclerView deviceRecycleView;
    private DeviceAdapter adapter;
    private ArrayList<Device> list = new ArrayList<>();
    private ArrayList<Device> copyList = new ArrayList<>();
    private ArrayList<Integer> positionList = new ArrayList<>();
    private String jsonName = "DeviceList.json";
    private String jsonNamePosition = "HistoryDeviceList.json";
    /**
     *  Executes code after open Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_history);
        EditText editSearch = findViewById(R.id.editSearch);
        this.deviceRecycleView = findViewById(R.id.devices);

//        Toolbar toolbar_devicecard = (Toolbar) findViewById(R.id.toolbar_devicecard);

        try {
            positionList = JsonHandler.getIntegerList(jsonNamePosition, this);
            copyList = JsonHandler.getDeviceList(jsonName, this);
            for(Integer i : positionList){
                try{
                    list.add(copyList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Collections.reverse(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupRecyclerView();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    //TODO Add improtant filters
    private void filter(String text) {
        ArrayList<Device> filteredList = new ArrayList<>();
        for (Device item : list) {
            if (item.getInventoryNumber().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getStatus().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getCategory().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getManufacturer().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getManufacturer().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getModel().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    /**
     * initialize RecyclerView with DeviceAdapter
     */
    private void setupRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.deviceRecycleView.setLayoutManager(mLayoutManager);

        adapter = new DeviceAdapter(list,this);
        this.deviceRecycleView.setAdapter(adapter);

        String show = list.size() + " Geräte wurden gefunden";
        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_SHORT).show();

    }

    /**
     * Starts Activity if Item is clicked
     * @param position
     */
    @Override
    public void onNoteClick(int position) throws IOException {
        Intent intent = new Intent(DeviceHistoryActivity.this, DeviceCardActivity.class);
        intent.putExtra("Device", list.get(position));
        intent.putExtra("isOldVersion", false);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();
    }
}
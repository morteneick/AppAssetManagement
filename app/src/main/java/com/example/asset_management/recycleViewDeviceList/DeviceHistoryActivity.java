package com.example.asset_management.recycleViewDeviceList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * RecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
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

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        try {
            positionList = JsonHandler.getIntegerList(jsonNamePosition, this);
            copyList = JsonHandler.getDeviceList(jsonName, this);
            for(Integer i : positionList){
                try{
                    for(Device device : copyList){
                        if(i == device.getInventoryNumberInt()){
                            list.add(device);
                        }
                    }

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

    /**
     * Filters the ArrayList<Device> if the device has the text in: InventoryNumber, Status, Category
     * Manufacturer and Model
     * @param text
     */
    /**
     * Checks each object in the list for each variable to see if it matches the input from the
     * textfield
     * @param text Text from the Textfield
     */
    private void filter(String text) {
        ArrayList<Device> filteredList = new ArrayList<>();

        for (Device item : list) {
            int i = 0;
            try{
                if (item.getInventoryNumber().toLowerCase().contains(text.toLowerCase())) {
                    i++;
                }
            } catch (Exception ignored){

            }

            try{
                if (item.getStatus().toLowerCase().contains(text.toLowerCase())) {
                    i++;
                }
            } catch (Exception ignored){

            }

            try {
                if (item.getCategory().toLowerCase().contains(text.toLowerCase())) {
                    i++;
                }
            } catch (Exception ignored){

            }

            try {
                if (item.getManufacturer().toLowerCase().contains(text.toLowerCase())) {
                    i++;
                }
            } catch (Exception ignored){

            }

            try {
                if (item.getModel().toLowerCase().contains(text.toLowerCase())) {
                    i++;
                }
            } catch (Exception ignored){

            }

            try {
                if(i > 0){
                    filteredList.add(item);
                }
            } catch (Exception ignored){

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
    }

    /**
     * Starts Activity if Item is clicked
     * @param position
     */
    @Override
    public void onNoteClick(int position) throws IOException {
        Intent intent = new Intent(DeviceHistoryActivity.this, DeviceCardActivity.class);
        intent.putExtra(getString(R.string.deviceName), list.get(position));
        intent.putExtra(getString(R.string.isOldVersion), false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
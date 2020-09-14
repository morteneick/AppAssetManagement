package com.example.asset_management.recycleView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.jsonhandler.JsonHandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * RecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */
public class RecycleActivity extends AppCompatActivity implements DeviceAdapter.OnNoteListener {
    private RecyclerView deviceRecycleView;
    private DeviceAdapter adapter;
    private String fileName = "HistoryDeviceList.json";
    private RequestQueue mQueue;
    private ArrayList<Device> list = new ArrayList<>();

    private String jsonName = "DeviceList.json";
    String url = "https://gist.githubusercontent.com/Dziersan/1766cd6c4ab4d61555e63cb34478d888/" +
            "raw/bddf90ce241a4632cd84d0046866f2cd91367c8b/0device.json";

    /**
     *  Executes code after open Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        Connection connection = new Connection();
        connection.getDeviceList(this);
        connection.getReservationList(this);

        EditText editSearch = findViewById(R.id.editSearch);
        this.deviceRecycleView = findViewById(R.id.devices);
        mQueue = Volley.newRequestQueue(this);

        try {
            list = JsonHandler.getDeviceList(jsonName, this);
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
     * Checks each object in the list for each variable to see if it matches the input from the
     * textfield
     * @param text Text from the Textfield
     */
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
     * @param position Integer from the clicked List
     */
    @Override
    public void onNoteClick(int position) throws IOException {

        Intent intent = new Intent(RecycleActivity.this, DeviceCardActivity.class);
        intent.putExtra("Device", list.get(position));
        intent.putExtra("isOldVersion", false);
        startActivity(intent);
        JsonHandler.createJsonFromDevice(list.get(position), "Device.json", this);
        setHistoryDeviceList(position);
    }

    /**
     * To edit the devicecard the activity need to restart so with this the user doesnt see a
     * restart.
     */
    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();
    }

    /**
     * Sets the editability of the device card to false after closing the activity
     */
    public void onStop () {

        SwitchEditable switchEditable = new SwitchEditable(false);
        JsonHandler.createJsonFromObject(switchEditable, "Switch.json", this);
        super.onStop();
    }


    /**
     * Adds the position from the onNoteClick to an array and saves it into an json file. So there
     * is an history of the clicked devices.
     * @param position position from the onNoteClick
     * @throws IOException
     */
    public void setHistoryDeviceList(int position) throws IOException {

        ArrayList<Integer> listHistory = new ArrayList<>();
        File file = this.getFileStreamPath(fileName);

        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),fileName);
            listHistory.add(position);
            JsonHandler.createJsonFromInteger(listHistory, fileName, this);
        } else {
            listHistory = JsonHandler.getIntegerList(fileName, this);
            int counter = 0;
            Integer remover = null;
            for(Integer i : listHistory){
                if(i == position){
                    remover = i;
                    counter++;
                }
            }
            if(counter == 0){
                listHistory.add(position);
            } else {
                listHistory.remove(remover);
                listHistory.add(position);
            }
            JsonHandler.createJsonFromInteger(listHistory,fileName,this);
        }
    }
}
package com.example.asset_management.recycleView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * RecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class RecycleActivity extends AppCompatActivity implements DeviceAdapter.OnNoteListener {
    private RecyclerView deviceRecycleView;
    private DeviceAdapter adapter;

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
        EditText editSearch = findViewById(R.id.editSearch);
        this.deviceRecycleView = findViewById(R.id.devices);
        mQueue = Volley.newRequestQueue(this);


        Connection.jsonParse(url, this);

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

//        JsonHandler.createJsonFromDeviceList(list, jsonName, this);
//        TextView textViewInfo = findViewById(R.id.textViewInfo);
    }


    /**
     * Starts Activity if Item is clicked
     * @param position
     */
    @Override
    public void onNoteClick(int position) throws IOException {
        String fileName = "HistoryDeviceList.json";
        ArrayList<Device> historyDeviceList = new ArrayList<>();

        Intent intent = new Intent(RecycleActivity.this, DeviceCardActivity.class);
        intent.putExtra("Device", list.get(position));
        startActivity(intent);

        File file = this.getFileStreamPath(fileName);

        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),fileName);
            historyDeviceList.add(list.get(position));
            JsonHandler.createJsonFromDeviceList(historyDeviceList,fileName,this);
        } else {
            int counter = 0;
            historyDeviceList = JsonHandler.getDeviceList(fileName, this);
            historyDeviceList.add(list.get(position));
            JsonHandler.createJsonFromDeviceList(historyDeviceList,fileName,this);
        }

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
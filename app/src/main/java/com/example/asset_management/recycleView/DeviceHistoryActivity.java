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
import java.util.Collections;

/**
 * RecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class DeviceHistoryActivity extends AppCompatActivity implements DeviceAdapter.OnNoteListener {
    private RecyclerView deviceRecycleView;
    private DeviceAdapter adapter;

    private ArrayList<Device> list = new ArrayList<>();

    private String jsonName = "HistoryDeviceList.json";

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

        try {
            list = JsonHandler.getDeviceList(jsonName, this);
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
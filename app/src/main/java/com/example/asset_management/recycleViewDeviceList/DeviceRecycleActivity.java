package com.example.asset_management.recycleViewDeviceList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * DeviceRecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */
public class DeviceRecycleActivity extends AppCompatActivity implements DeviceAdapter.OnNoteListener {
    private RecyclerView deviceRecycleView;
    private DeviceAdapter adapter;
    private RequestQueue mQueue;
    private ArrayList<Device> list = new ArrayList<>();
    private Boolean isFiltered;
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     *  Executes code after open Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelist);

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Connection connection = new Connection();
        connection.getDeviceList(this);
        connection.getReservationList(this);

        EditText editSearch = findViewById(R.id.editSearch);
        this.deviceRecycleView = findViewById(R.id.devices);
        String deviceListName = getString(R.string.deviceListNameJSON);
        String putExtraList = getString(R.string.filteredList);

        File file = this.getFileStreamPath(deviceListName);
        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),deviceListName);
        }

        try {
            list = (ArrayList<Device>) intent.getSerializableExtra(putExtraList);
            isFiltered = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list == null){
            try {
                list = JsonHandler.getDeviceList(deviceListName, this);
                isFiltered = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextView lastUpdate = findViewById(R.id.textLastUpdate);
            Calendar calendar = Calendar.getInstance();
            String lastUpdateNameJson = getString(R.string.lastUpdateNameJSON);
            try {
                calendar = JsonHandler.getCalendar(lastUpdateNameJson, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        lastUpdate.setText(getString(R.string.lastUpdateMessage) + calendar.getTime().toString());
        }

        setupRecyclerView();
        mQueue = Volley.newRequestQueue(this);

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

        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Connection connection = new Connection();
                connection.getDeviceList(getApplicationContext());
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
            }
        });
    }

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

//        String show = list.size() + " Geräte wurden gefunden";
//        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_SHORT).show();
    }

    /**
     * Starts Activity if Item is clicked
     * @param position Integer from the clicked List
     */
    @Override
    public void onNoteClick(int position) throws IOException {
        String deviceListNameJson = getString(R.string.deviceListNameJSON);
        String deviceNameJson = getString(R.string.deviceNameJSON);
        String deviceName = getString(R.string.deviceName);
        String isOldVersion = getString(R.string.isOldVersion);
        Intent intent = new Intent(DeviceRecycleActivity.this,
                DeviceCardActivity.class);
        if(isFiltered){
            ArrayList<Device> deviceList = JsonHandler.getDeviceList(deviceListNameJson, this);
            for(Device d : deviceList){
                if(list.get(position).getInventoryNumber().equals(d.getInventoryNumber())){
                    intent.putExtra(deviceName, d);
                    JsonHandler.createJsonFromDevice(d, deviceNameJson, this);
                }
            }

        } else {
            intent.putExtra(deviceName, list.get(position));
            JsonHandler.createJsonFromDevice(list.get(position), deviceNameJson, this);
            setHistoryDeviceList(position);
        }

        intent.putExtra(isOldVersion, false);
        startActivity(intent);
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
        String switchNameJson = getString(R.string.switchNameJSON);
        JsonHandler.createJsonFromObject(switchEditable, switchNameJson, this);
        super.onStop();
    }


    /**
     * Adds the position from the onNoteClick to an array and saves it into an json file. So there
     * is an history of the clicked devices.
     * @param position position from the onNoteClick
     * @throws IOException
     */
    public void setHistoryDeviceList(int position) throws IOException {
        String historyDeviceListNameJson = getString(R.string.historyDeviceListNameJSON);
        ArrayList<Integer> listHistory = new ArrayList<>();
        File file = this.getFileStreamPath(historyDeviceListNameJson);

        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(), historyDeviceListNameJson);
            listHistory.add(position);
            JsonHandler.createJsonFromInteger(listHistory, historyDeviceListNameJson,
                    this);
        } else {
            listHistory = JsonHandler.getIntegerList(historyDeviceListNameJson, this);
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
            JsonHandler.createJsonFromInteger(listHistory, historyDeviceListNameJson,
                    this);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycleview_devicelist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String lastUpdateNameJson = getString(R.string.lastUpdateNameJSON);
        if (id == R.id.action_filter) {
            Intent intent = new Intent (this, FilterDeviceListActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_reload) {
            Calendar calendar = Calendar.getInstance();
            JsonHandler.createJsonFromCalendar(calendar, lastUpdateNameJson,
                    this);
            Connection connection = new Connection();
            connection.getDeviceList(this);
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
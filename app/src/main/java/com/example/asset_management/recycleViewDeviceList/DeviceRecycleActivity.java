package com.example.asset_management.recycleViewDeviceList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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
    private String fileName = "HistoryDeviceList.json";
    private RequestQueue mQueue;
    private ArrayList<Device> list = new ArrayList<>();
    private String jsonName = "DeviceList.json";

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

        File file = this.getFileStreamPath(jsonName);
        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),jsonName);
        }

        try {
            list = (ArrayList<Device>) intent.getSerializableExtra("filteredList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list == null){
            try {
                list = JsonHandler.getDeviceList(jsonName, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            int i = 0;
            if (item.getInventoryNumber().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if (item.getStatus().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if (item.getCategory().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if (item.getManufacturer().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if (item.getManufacturer().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if (item.getModel().toLowerCase().contains(text.toLowerCase())) {
                i++;
            }
            if(i > 0){
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

//        String show = list.size() + " Geräte wurden gefunden";
//        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_SHORT).show();
    }

    /**
     * Starts Activity if Item is clicked
     * @param position Integer from the clicked List
     */
    @Override
    public void onNoteClick(int position) throws IOException {

        Intent intent = new Intent(DeviceRecycleActivity.this, DeviceCardActivity.class);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycleview_devicelist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Intent intent = new Intent (this, FilterDeviceListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
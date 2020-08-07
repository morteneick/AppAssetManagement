package com.example.asset_management.recycleView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    private ArrayList<Device> list = new ArrayList<>();
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

        mQueue = Volley.newRequestQueue(this);

        this.deviceRecycleView = findViewById(R.id.devices);

        jsonParse(url);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.deviceRecycleView.setLayoutManager(mLayoutManager);
        adapter = new DeviceAdapter(list,this);
        this.deviceRecycleView.setAdapter(adapter);

    }

    /**
     * initialize RecyclerView with DeviceAdapter
     */
    private void setupRecyclerView(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.deviceRecycleView.setLayoutManager(mLayoutManager);

        adapter = new DeviceAdapter(list,this);
        this.deviceRecycleView.setAdapter(adapter);

        String show = list.size() + " Geräte wurden gefunden";
        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_SHORT).show();
}

    /**
     * Opens JSON-formatted URL, creates Device objectives and saves them into a list.
     */
    public void jsonParse(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("device");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonDevice = jsonArray.getJSONObject(i);
                                Device device = new Device();

                                device.setInventoryNumber(jsonDevice.getString
                                        ("inventoryNumber"));
                                device.setManufacturer(jsonDevice.getString("manufacturer"));
                                device.setModel(jsonDevice.getString("model"));
                                device.setStatus(jsonDevice.getString("status"));
                                device.setDeviceCategorie(jsonDevice.getString
                                        ("deviceCategorie"));
                                list.add(device);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setupRecyclerView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Keine Verbindung gefunden",
                        Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mQueue = Volley.newRequestQueue(RecycleActivity.this);
        mQueue.add(request);
    }

    /**
     * Starts Activity if Item is clicked
     * @param position
     */
    @Override
    public void onNoteClick(int position) {
        Toast.makeText(getApplicationContext(),list.get(position).getInventoryNumber(),
                Toast.LENGTH_SHORT).show();
//        list.get(position);
//        Intent intent = new Intent(this. NewActivity.java);
//        startactivity
    }
}

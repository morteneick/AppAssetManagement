package com.example.asset_management.recycleViewDeviceList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.asset_management.R;
import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FilterDeviceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_device_list);



        final Spinner spinner = findViewById(R.id.spinnerStatus);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button btnFilter = findViewById(R.id.btnFilter);

        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(7);
            }
        });



        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = spinner.getSelectedItem().toString();

                ArrayList<Device> unfilteredList = new ArrayList<Device>();
                ArrayList<Device> filteredList = new ArrayList<Device>();
                try {
                    unfilteredList = JsonHandler.getDeviceList("DeviceList.json",
                            getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(Device d : unfilteredList){
                    if(d.getStatus().equals(status)){
                        filteredList.add(d);
                    }
                }
                finish();
                Intent intent = new Intent(FilterDeviceListActivity.this,
                        DeviceRecycleActivity.class);
                intent.putExtra("filteredList", (Serializable) filteredList);
                startActivity(intent);
            }
        });
    }
}
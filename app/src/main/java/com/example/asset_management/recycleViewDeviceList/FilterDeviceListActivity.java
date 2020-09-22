package com.example.asset_management.recycleViewDeviceList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.ui.reservation.DatePickerFragment;
import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * FilterDeviceListActivity
 * <p>
 *     Version 1.0
 * </p>
 * 14.09.2020
 * AUTHOR: Dominik Dziersan
 */
public class FilterDeviceListActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {
    private boolean isStart;
    private Calendar startCalendar;
    private Calendar endCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_device_list);


        Button btnStart = findViewById(R.id.btnStartDateTuev);
        Button btnEnd = findViewById(R.id.btnEndDateTuev);

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

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");
                isStart = true;
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "End");
                isStart = false;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = spinner.getSelectedItem().toString();
                Date startDate = null;
                Date endDate = null;

                try {
                    startDate = startCalendar.getTime();
                } catch ( Exception ignored) {

                }
                try {
                    endDate = endCalendar.getTime();
                } catch ( Exception ignored) {

                }

                ArrayList<Device> unfilteredList = new ArrayList<Device>();
                ArrayList<Device> filteredList = new ArrayList<Device>();
                try {
                    unfilteredList = JsonHandler.getDeviceList("DeviceList.json",
                            getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(Device d : unfilteredList){
                    int i = 0;
                    try{
                        if(!status.equals("")){
                            if(status.equals(d.getStatus())){
                                i++;
                            } else {
                                continue;
                            }
                        }
                        if(startDate != null){
                            if(startDate.compareTo(d.getLastTuev()) < 0){
                                i++;
                            } else {
                                continue;
                            }
                        }
                        if(endDate != null){
                            if(endDate.compareTo(d.getLastTuev()) > 0){
                                i++;
                            } else {
                                continue;
                            }
                        }
                        if(i > 0){
                            filteredList.add(d);
                        }
                    } catch (Exception e) {

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textStart = findViewById(R.id.textTuevStart);
        TextView textEnd = findViewById(R.id.textTuevEnd);

        if(isStart){
            startCalendar = c;
            textStart.setText(currentDateString);
        } else {
            endCalendar = c;
            textEnd.setText(currentDateString);

            if(startCalendar != null
                && startCalendar.compareTo(c) >= 0){
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
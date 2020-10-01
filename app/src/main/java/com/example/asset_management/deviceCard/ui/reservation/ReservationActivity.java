package com.example.asset_management.deviceCard.ui.reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * ReservationActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */

public class ReservationActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {
    private boolean isStart;
    private String fileName = "Reservation.json";
    ArrayList<Reservation> list = new ArrayList<>();
    Reservation reservation = new Reservation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Button buttonStart = findViewById(R.id.btnStartDate);
        Button buttonReserve = findViewById(R.id.btnReserve);
        Button buttonEnd = findViewById(R.id.btnEndDate);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");
                isStart = true;
            }
        });

        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textStart = findViewById(R.id.textReservationStart);
                TextView textEnd = findViewById(R.id.textReservationEnd);
                String start = textStart.getText().toString();
                String end = textEnd.getText().toString();
                try {
                    reservation.isCorrectFilled(start, end, reservation,getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "End");
                isStart = false;
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
        TextView textStart = findViewById(R.id.textReservationStart);
        TextView textEnd = findViewById(R.id.textReservationEnd);
        EditText editConstruction = findViewById(R.id.editConstruction);
        String inventoryNumber = null;

        reservation.setBuildingSite(editConstruction.getText().toString());
        try {
            Device device = JsonHandler.getDevice
                    ("Device.json",this);
            inventoryNumber = device.getInventoryNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
//TODO add name surname
        reservation.setInventoryNumber(inventoryNumber);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if(isStart){
            date = c.getTime();
            textStart.setText(format.format(date));
            reservation.setStart(c);
            reservation.setLoanDay(date);
        } else {
            if(reservation.getStart().compareTo(c) <= 0){
                date = c.getTime();
                textEnd.setText(format.format(date));
                reservation.setLoanEnd(date);
                reservation.setStart(null);
                list.add(reservation);
                JsonHandler.createJsonFromCalendarList(list,fileName, this);
            } else {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }


}

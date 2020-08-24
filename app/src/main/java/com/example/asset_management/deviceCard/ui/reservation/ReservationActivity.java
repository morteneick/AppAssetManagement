package com.example.asset_management.deviceCard.ui.reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private boolean isStart;
    private String fileName = "Reservation.json";
    ArrayList<Calendar> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_test);

        Button buttonStart = (Button) findViewById(R.id.btnStartDate);
        Button buttonReserve = (Button) findViewById(R.id.btnReserve);
        Button buttonEnd = (Button) findViewById(R.id.btnEndDate);


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
                TextView textStart = (TextView) findViewById(R.id.textReservationStart);
                TextView textEnd = (TextView) findViewById(R.id.textReservationEnd);
            isCorrectFilled(textStart, textEnd);
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
        TextView textStart = (TextView) findViewById(R.id.textReservationStart);
        TextView textEnd = (TextView) findViewById(R.id.textReservationEnd);
        if(isStart){
            textStart.setText(currentDateString);
            list.add(c);
            JsonHandler.createJsonFromCalendarList(list,fileName, this);
        } else {
            textEnd.setText(currentDateString);
            try {
                list = JsonHandler.getCalendarList(fileName, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(list.get(0).compareTo(c) <= 0){
                if(list.size() >= 2){
                    list.remove(1);
                }
                list.add(c);
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

    public boolean isCorrectFilled (TextView textStart, TextView textEnd){

        String start = textStart.getText().toString();
        String end = textEnd.getText().toString();

        if (start.equals("")
                || end.equals("")) {
            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}

package com.example.asset_management.deviceCard.ui.reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservationTest extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private boolean isStart;
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
        } else {
            textEnd.setText(currentDateString);
        }
    }
}

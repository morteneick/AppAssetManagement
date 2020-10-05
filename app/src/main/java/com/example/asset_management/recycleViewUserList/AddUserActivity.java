package com.example.asset_management.recycleViewUserList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;

import java.io.IOException;
import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        final EditText editWorkerId = findViewById(R.id.editWorkerId);
        final EditText editFirstname = findViewById(R.id.editFirstname);
        final EditText editSurname = findViewById(R.id.editSurname);
        final EditText editRole = findViewById(R.id.editRole);
        final EditText editEmail = findViewById(R.id.editEmail);
        final EditText editPassword = findViewById(R.id.editPassword);
        final CheckBox checkAddDevice = findViewById(R.id.checkBoxAddDevice);
        final CheckBox checkEditDevice = findViewById(R.id.checkBoxEditDevice);
        final CheckBox checkDeleteDevice = findViewById(R.id.checkBoxDeleteDevice);
        final CheckBox checkViewDevice = findViewById(R.id.checkBoxViewDevice);
        final CheckBox checkAddUser = findViewById(R.id.checkBoxAddUser);
        final CheckBox checkEditUser = findViewById(R.id.checkBoxEditUser);
        final CheckBox checkDeleteUser = findViewById(R.id.checkBoxDeleteUser);
        final CheckBox checkAddReservation = findViewById(R.id.checkBoxReserveDevice);
        final CheckBox checkDeleteReservation = findViewById(R.id.checkBoxDeleteReservation);
        final CheckBox checkEditReservation = findViewById(R.id.checkBoxEditReservation);

        Button btnSave = findViewById(R.id.btnSave);
        View viewSave = findViewById(R.id.btnSave);
        viewSave.setVisibility(View.VISIBLE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = new UserInfo();
                try{
                    user.setWorkerId(editWorkerId.toString());
                } catch (Exception e) {
                    user.setWorkerId(user.getWorkerId());
                }
                user.setFirstname(editFirstname.getText().toString());
                user.setSurname(editSurname.getText().toString());
                user.seteMail(editEmail.getText().toString());
                user.setRole(editRole.getText().toString());
                user.setPassword(editPassword.getText().toString());
                user.setAddDevice(user.boolToInt(checkAddDevice.isChecked()));
                user.setEditDevice(user.boolToInt(checkEditDevice.isChecked()));
                user.setDeleteDevice(user.boolToInt(checkDeleteDevice.isChecked()));
                user.setViewDevice(user.boolToInt(checkViewDevice.isChecked()));
                user.setAddUser(user.boolToInt(checkAddUser.isChecked()));
                user.setEditUser(user.boolToInt(checkEditUser.isChecked()));
                user.setDeleteUser(user.boolToInt(checkDeleteUser.isChecked()));
                user.setBookingDevice(user.boolToInt(checkAddReservation.isChecked()));
                user.setEditBooking(user.boolToInt(checkEditReservation.isChecked()));
                user.setDeleteBooking(user.boolToInt(checkDeleteReservation.isChecked()));


                Connection connection = new Connection();
                connection.postNewUser(user, getApplicationContext());

                finish();
                overridePendingTransition(0, 0);
            }
        });
    }
}
package com.example.asset_management.recycleViewUserList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.deviceCard.ui.card.CardFragment;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;


public class UserCardActivity extends AppCompatActivity {
    public boolean onOffSwitch = false;
    private UserInfo user = new UserInfo();
    String saveMessage = "Informationen wurden geändert.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);


        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        final EditText editWorkerId = findViewById(R.id.editWorkerId);
        final EditText editFirstname = findViewById(R.id.editFirstname);
        final EditText editSurname = findViewById(R.id.editSurname);
        final EditText editRole = findViewById(R.id.editRole);
        final EditText editEmail = findViewById(R.id.editEmail);
        Intent intent = getIntent();
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

        user = (UserInfo) intent.getSerializableExtra("User");

        checkAddDevice.setChecked(user.intToBool(user.getAddDevice()));
        checkViewDevice.setChecked(user.intToBool(user.getViewDevice()));
        checkEditDevice.setChecked(user.intToBool(user.getEditDevice()));
        checkDeleteDevice.setChecked(user.intToBool(user.getDeleteDevice()));
        checkAddUser.setChecked(user.intToBool(user.getAddUser()));
        checkEditUser.setChecked(user.intToBool(user.getEditUser()));
        checkDeleteUser.setChecked(user.intToBool(user.getDeleteUser()));
        checkAddReservation.setChecked(user.intToBool(user.getBookingDevice()));
        checkEditReservation.setChecked(user.intToBool(user.getEditBooking()));
        checkDeleteReservation.setChecked(user.intToBool(user.getDeleteBooking()));

        editWorkerId.setText(user.getWorkerIdString());
        editFirstname.setText(user.getFirstname());
        editSurname.setText(user.getSurname());
        editRole.setText(user.getRole());
        editEmail.setText(user.geteMail());

        if(!isClicked()){
            CardFragment.blockInput(editFirstname);
            CardFragment.blockInput(editSurname);
            CardFragment.blockInput(editWorkerId);
            CardFragment.blockInput(editRole);
            CardFragment.blockInput(editEmail);
            viewSave.setVisibility(View.INVISIBLE);
            checkAddDevice.setClickable(false);
            checkEditDevice.setClickable(false);
            checkDeleteDevice.setClickable(false);
            checkViewDevice.setClickable(false);
            checkAddUser.setClickable(false);
            checkEditUser.setClickable(false);
            checkDeleteUser.setClickable(false);
            checkEditReservation.setClickable(false);
            checkDeleteReservation.setClickable(false);
            checkAddReservation.setClickable(false);
        } else {
            CardFragment.unblockInput(editFirstname);
            CardFragment.unblockInput(editSurname);
            CardFragment.unblockInput(editWorkerId);
            CardFragment.unblockInput(editRole);
            CardFragment.unblockInput(editEmail);
            viewSave.setVisibility(View.VISIBLE);
            checkAddDevice.setClickable(true);
            checkEditDevice.setClickable(true);
            checkDeleteDevice.setClickable(true);
            checkViewDevice.setClickable(true);
            checkAddUser.setClickable(true);
            checkEditUser.setClickable(true);
            checkDeleteUser.setClickable(true);
            checkEditReservation.setClickable(true);
            checkDeleteReservation.setClickable(true);
            checkAddReservation.setClickable(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = new UserInfo();
                try{
                    user.setWorkerId(editWorkerId.toString());
                } catch (Exception e) {
                    user.setWorkerId(user.getWorkerId());
                }
                user.setFirstname(editFirstname.toString());
                user.setSurname(editSurname.toString());
                user.seteMail(editEmail.toString());
                user.setRole(editRole.toString());
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
                connection.putUpdateUser(user, getApplicationContext());
                ArrayList<UserInfo> list = new ArrayList<>();
                try {
                    list = JsonHandler.getUserList("UserList.json",
                            getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SwitchEditable switchEditable = new SwitchEditable(false);
                createSwitch(switchEditable);
                finish();
                overridePendingTransition(0, 0);
                Toast.makeText(getApplicationContext(),saveMessage,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usercard, menu);
        return true;
    }

    public boolean createSwitch(Object object){
        JsonHandler.createJsonFromObject(object,"Switch.json",this);
        return onOffSwitch;
    }

    public String getSwitch() throws IOException {
        return JsonHandler.getListString(this, "Switch.json");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SwitchEditable switchEditable = new SwitchEditable(true);
            createSwitch(switchEditable);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            return true;
        }
        if(id == R.id.action_delete){
            new AlertDialog.Builder(this)
                    .setTitle("Gerät löschen")
                    .setMessage("Sind Sie sich sicher, dass Sie den Benutzer löschen möchten?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Connection connection = new Connection();
                            connection.deleteUser(user, getApplicationContext());
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean isClicked(){
        String isEditable = "";
        try {
            isEditable = getSwitch();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        final SwitchEditable switchEditable = gson.fromJson(isEditable, SwitchEditable.class);
        return switchEditable.isEnabled();
    }

    @Override
    public void onBackPressed() {
        onOffSwitch = isClicked();
        if(onOffSwitch){
            new AlertDialog.Builder(this)
                    .setTitle("Änderungen verwerfen")
                    .setMessage("Sind Sie sich sicher, dass Sie die Änderungen verwerfen möchten?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SwitchEditable switchEditable = new SwitchEditable(false);
                            createSwitch(switchEditable);
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            finish();
        }
    }
}
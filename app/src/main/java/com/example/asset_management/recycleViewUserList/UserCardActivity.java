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
import com.example.asset_management.mainHub.MainHubActivity;

import java.io.IOException;
import java.util.ArrayList;


public class UserCardActivity extends AppCompatActivity {
    public boolean onOffSwitch = false;
    private UserInfo user = new UserInfo();

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

        user = (UserInfo) intent.getSerializableExtra(getString(R.string.user));

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

        if(!SwitchEditable.isClicked(this)){
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
                user.setFirstname(editFirstname.getText().toString());
                user.setSurname(editSurname.getText().toString());
                user.seteMail(editEmail.getText().toString());
                user.setRole(editRole.getText().toString());
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
                    list = JsonHandler.getUserList(getString(R.string.userListNameJSON),
                            getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SwitchEditable switchEditable = new SwitchEditable(false);
                SwitchEditable.createSwitch(switchEditable, getApplicationContext());
                finish();
                overridePendingTransition(0, 0);
                Toast.makeText(getApplicationContext(),
                        getString(R.string.saveMessage),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        UserInfo user = MainHubActivity.getUser();
        if(user.intToBool(user.getDeleteUser())
                && user.intToBool(user.getEditUser())){
            getMenuInflater().inflate(R.menu.menu_usercard_all, menu);
            return true;
        }
        if(user.intToBool(user.getDeleteDevice())){
            getMenuInflater().inflate(R.menu.menu_usercard_delete, menu);
            return true;
        }
        if(user.intToBool(user.getEditDevice())){
            getMenuInflater().inflate(R.menu.menu_usercard_edit, menu);
            return true;
        }
        return true;
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
            SwitchEditable.createSwitch(switchEditable, this);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            return true;
        }
        if(id == R.id.action_delete){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.deleteUserTitle))
                    .setMessage(getString(R.string.deleteUserText))

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            user = (UserInfo) getIntent().getSerializableExtra(
                                    getString(R.string.user));
                            Connection connection = new Connection();
                            connection.deleteUser(user, getApplicationContext());
                            connection.getAllUsers(getApplicationContext());
                            finish();
                        }
                    })

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onOffSwitch = SwitchEditable.isClicked(this);
        if(onOffSwitch){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.discardTitle))
                    .setMessage(getString(R.string.discardText))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SwitchEditable switchEditable = new SwitchEditable(false);
                            SwitchEditable.createSwitch(switchEditable, getApplicationContext());
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            finish();
        }
    }
}
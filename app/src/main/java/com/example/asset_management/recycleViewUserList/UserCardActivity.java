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

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.deviceCard.ui.card.CardFragment;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.FilterDeviceListActivity;
import com.google.gson.Gson;

import java.io.IOException;


public class UserCardActivity extends AppCompatActivity {
    public boolean onOffSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);


        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        EditText editWorkerId = findViewById(R.id.editWorkerId);
        EditText editFirstname = findViewById(R.id.editFirstname);
        EditText editSurname = findViewById(R.id.editSurname);
        EditText editRole = findViewById(R.id.editRole);
        EditText editEmail = findViewById(R.id.editEmail);
        Intent intent = getIntent();
        CheckBox checkAddDevice = findViewById(R.id.checkBoxAddDevice);

        Button btnSave = findViewById(R.id.btnSave);
        View viewSave = findViewById(R.id.btnSave);

        UserInfo user = (UserInfo) intent.getSerializableExtra("User");

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
        } else {
            CardFragment.unblockInput(editFirstname);
            CardFragment.unblockInput(editSurname);
            CardFragment.unblockInput(editWorkerId);
            CardFragment.unblockInput(editRole);
            CardFragment.unblockInput(editEmail);
            viewSave.setVisibility(View.VISIBLE);
        }

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
                    .setMessage("Sind Sie sich sicher, dass Sie ddie Änderungen verwerfen möchten?")

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
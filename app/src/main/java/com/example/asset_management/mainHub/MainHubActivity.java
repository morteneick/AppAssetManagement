package com.example.asset_management.mainHub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.example.asset_management.R;

import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.login.LoginActivity;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.settings.SettingsActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * MainHubActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class MainHubActivity extends AppCompatActivity {
    static UserInfo user = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userPutExta = getString(R.string.user);
        user =  (UserInfo) intent.getSerializableExtra(userPutExta);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        Connection connection = new Connection();
        connection.getDeviceList(this);
        SwitchEditable switchEditable = new SwitchEditable(false);
        SwitchEditable.createSwitch(switchEditable, this);

        try {
            user = getUser();
        } catch (Exception e){

        }
        if(user == null){
            Intent loginIntent =new Intent(MainHubActivity.this, LoginActivity.class);
            finish();
            startActivity(loginIntent);
        } else {
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                showStartDialog();
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.welcomeTitle))
                .setMessage(getString(R.string.welcomeText))
                .setPositiveButton(getString(R.string.alertDialogAcceptButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(getString(R.string.url)));
                        startActivity(browserIntent);
                        dialog.dismiss();
                    }
                })
                .create().show();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    public static UserInfo getUser(){
        return user;
    }

}

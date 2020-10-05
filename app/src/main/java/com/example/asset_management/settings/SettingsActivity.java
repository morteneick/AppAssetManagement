package com.example.asset_management.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.login.LoginActivity;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.mainHub.MainHubActivity;
import com.example.asset_management.mainHub.MainHubFragment;
import com.example.asset_management.recycleViewUserList.UserRecycleActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().commit();

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        ArrayList<String> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listOptions);

        Connection connection = new Connection();
        connection.getAllUsers(this);

        list.add(getString(R.string.settings0));
        list.add(getString(R.string.settings1));
        list.add(getString(R.string.settings2));
        list.add(getString(R.string.settings3));

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        Connection connection = new Connection();
                        connection.getAllUsers(getApplicationContext());
                        Intent intent = new Intent(SettingsActivity.this,
                                UserRecycleActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent login = new Intent(SettingsActivity.this,
                                LoginActivity.class);
                        startActivity(login);
                        break;
                    case 3:
                        //http://localhost:3000/FAQ
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(getString(R.string.url)));
                        startActivity(browserIntent);
                        break;
                    default:
                }
            }
        });
    }
}

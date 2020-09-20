package com.example.asset_management.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.recycleViewUserList.UserRecycleActivity;

import java.util.ArrayList;
/**
 * SettingsActivity
 * <p>
 *     Version 1.0
 * </p>
 * 09.09.2020
 * AUTHOR: Dominik Dziersan
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().commit();

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        final ArrayList<String> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listOptions);

        list.add("Nutzer bearbeiten");
        list.add("Benutzerdokumentation");
        list.add("Informationen zur App");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menuItem = list.get(position);
                switch(menuItem){
                    case "Nutzer bearbeiten":
                        Connection connection = new Connection();
                        connection.getAllUsers(getApplicationContext());
                        Intent intent = new Intent(SettingsActivity.this,
                                UserRecycleActivity.class);
                        startActivity(intent);
                        break;
                    case "Benutzerdokumentation":
                        break;
                    case "Informationen zur App":
                        break;
                    default:
                }
            }
        });
    }
}


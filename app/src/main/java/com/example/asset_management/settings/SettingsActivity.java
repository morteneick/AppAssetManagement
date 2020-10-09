package com.example.asset_management.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.mainHub.MainHubActivity;
import com.example.asset_management.recycleViewUserList.UserRecycleActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    UserInfo user = new UserInfo();
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
        list.add(getString(R.string.settings3));

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = MainHubActivity.getUser();
                switch(position){
                    case 0:
                        if(user.intToBool(user.getAddUser())
                        ||user.intToBool(user.getDeleteUser())){
                            Connection connection = new Connection();
                            connection.getAllUsers(getApplicationContext());
                            Intent intent = new Intent(SettingsActivity.this,
                                    UserRecycleActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.noAccessMessage),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        //http://localhost:3000/FAQ
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(getString(R.string.urlHelp)));
                        startActivity(browserIntent);
                        break;
                    default:
                }
            }
        });
    }
}

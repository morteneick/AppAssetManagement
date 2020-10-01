package com.example.asset_management.recycleViewUserList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.FilterDeviceListActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * RecycleActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */
public class UserRecycleActivity extends AppCompatActivity implements UserAdapter.OnNoteListener {
    private RecyclerView userRecycleView;
    private UserAdapter adapter;
    private RequestQueue mQueue;
    private ArrayList<UserInfo> list = new ArrayList<>();

    /**
     *  Executes code after open Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        Connection connection = new Connection();
        connection.getDeviceList(this);
        connection.getReservationList(this);

        EditText editSearch = findViewById(R.id.editSearch);
        this.userRecycleView = findViewById(R.id.users);

        String userListNameJson = getString(R.string.userListNameJSON);
        try {
            list = JsonHandler.getUserList(userListNameJson, this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        setupRecyclerView();
        mQueue = Volley.newRequestQueue(this);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    /**
     * Checks each object in the list for each variable to see if it matches the input from the
     * textfield
     * @param text Text from the Textfield
     */
//TODO Add improtant filters
    private void filter(String text) {
        ArrayList<UserInfo> filteredList = new ArrayList<>();
        for (UserInfo item : list) {
            if (item.getFirstname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getSurname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getRole().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getWorkerIdString().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    /**
     * initialize RecyclerView with DeviceAdapter
     */
    private void setupRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.userRecycleView.setLayoutManager(mLayoutManager);

        adapter = new UserAdapter(list,this);
        this.userRecycleView.setAdapter(adapter);

        String show = list.size() + getString(R.string.numbersUserFound);
        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_SHORT).show();
    }

    /**
     * Starts Activity if Item is clicked
     * @param position Integer from the clicked List
     */
    @Override
    public void onNoteClick(int position){

        Intent intent = new Intent(UserRecycleActivity.this, UserCardActivity.class);
        UserInfo user = list.get(position);
        intent.putExtra(getString(R.string.user), user);
        startActivity(intent);

    }

    /**
     * To edit the devicecard the activity need to restart so with this the user doesnt see a
     * restart.
     */
    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();
    }

    /**
     * Sets the editability of the device card to false after closing the activity
     */
    public void onStop () {
        SwitchEditable switchEditable = new SwitchEditable(false);
        JsonHandler.createJsonFromObject(switchEditable, getString(R.string.switchNameJSON),
                this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycleview_userlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent (this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_reload) {
            Calendar calendar = Calendar.getInstance();
            JsonHandler.createJsonFromCalendar(calendar, getString(R.string.lastUpdateNameJSON),
                    this);
            Connection connection = new Connection();
            connection.getDeviceList(this);
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
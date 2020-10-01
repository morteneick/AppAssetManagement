package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;


import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.deviceCard.ui.card.CardFragment;
import com.example.asset_management.deviceCard.ui.reservation.DatePickerFragment;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.asset_management.deviceCard.ui.card.CardFragment.setVisibility;


/**
 * AddDeviceActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */

public class AddDeviceActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {
    private String jsonName = "NewDevice.json";
    private String clickedCalendar = "";
    private Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
    Date date = new Date();

    EditText editGuarantee;
    EditText editTuev;
    EditText editUvv;
    EditText editRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);


        final EditText editInventoryNumber = findViewById(R.id.editInventoryNumber);
        final Spinner editStatus = findViewById(R.id.editStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        editStatus.setAdapter(adapter);

        final EditText editManufacturer = findViewById(R.id.editManufacturer);
        final EditText editModel = findViewById(R.id.editModel);
        final EditText editSerialnumber = findViewById(R.id.editSerialnumber);
        final EditText editCategory = findViewById(R.id.editCategory);
        final EditText editName = findViewById(R.id.editName);
        final EditText editCity = findViewById(R.id.editCity);
        final EditText editPostcode = findViewById(R.id.editPostcode);
        final EditText editStreet = findViewById(R.id.editStreet);
        final EditText editNotes = findViewById(R.id.editNotes);
        final EditText editProject = findViewById(R.id.editProject);
        final EditText editRepairNotes = findViewById(R.id.editRepairNotes);
        final EditText editBeaconMinor = findViewById(R.id.editBeaconMinor);
        final EditText editBeaconMajor = findViewById(R.id.editBeaconMajor);
        editGuarantee = findViewById(R.id.editGuarantee);
        editTuev = findViewById(R.id.editTuev);
        editUvv = findViewById(R.id.editUvv);
        editRepair = findViewById(R.id.editRepair);

        final Button btnTuev = findViewById(R.id.btnTuev);
        final View viewTuev = findViewById(R.id.btnTuev);
        final View viewSave = findViewById(R.id.btnSave);
        final Button btnSave = findViewById(R.id.btnSave);
        final Button btnUvv = findViewById(R.id.btnUvv);
        final View viewUvv = findViewById(R.id.btnUvv);
        final Button btnRepair = findViewById(R.id.btnRepair);
        final View viewRepair = findViewById(R.id.btnRepair);
        final Button btnGuarantee = findViewById(R.id.btnGuarantee);
        final View viewGuarantee = findViewById(R.id.btnGuarantee);

        btnTuev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "tuev";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");
            }
        });
        btnGuarantee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "guarantee";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");
            }
        });

        btnUvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "uvv";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");
            }
        });

        btnRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "repair";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Start");

            }
        });

        setVisibility(editTuev, btnTuev);
        setVisibility(editUvv, btnUvv);
        setVisibility(editRepair, btnRepair);
        setVisibility(editGuarantee, btnGuarantee);
        viewSave.setVisibility(View.VISIBLE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inventoryNumber = editInventoryNumber.getText().toString();
                String serialnumber = editSerialnumber.getText().toString();
                String manufacturer = editManufacturer.getText().toString();
                String model = editModel.getText().toString();
                String status;
                if (editStatus.getSelectedItem().toString().equals("")) {
                    status = "Verfügbar";
                }
                status = editStatus.getSelectedItem().toString();
                String category = editCategory.getText().toString();
                String name = editName.getText().toString();
                String street = editStreet.getText().toString();
                String city = editCity.getText().toString();
                String notes = editNotes.getText().toString();
                String tuev = editTuev.getText().toString();
                String uvv = editUvv.getText().toString();
                String guarantee = editGuarantee.getText().toString();
                String project = editProject.getText().toString();
                String repair = editRepair.getText().toString();
                String repairNote = editRepairNotes.getText().toString();
                String beaconMinor = editBeaconMinor.getText().toString();
                String beaconMajor = editBeaconMajor.getText().toString();

                Device device = new Device();

                device.setInventoryNumber(inventoryNumber);
                device.setSerialnumber(serialnumber);
                device.setModel(model);
                device.setStatus(status);
                device.setCategory(category);
                device.setManufacturer(manufacturer);
                device.setNote(notes);
                device.setCity(city);
                device.setStreet(street);
                device.setName(name);
                device.setBeaconMinor(beaconMinor);
                device.setBeaconMajor(beaconMajor);
                device.setRepairNote(repairNote);
                device.setDeviceStatus(CardFragment.getPosition(status)+1);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
                Date date = null;

                try {
                    date = format.parse(repair);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                device.setLastRepair(date);
                date = null;

                try {
                    date = format.parse(tuev);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                device.setLastTuev(date);
                date = null;

                try {
                    date = format.parse(uvv);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                device.setLastUvv(date);

                try {
                    date = format.parse(guarantee);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                device.setLastUvv(date);

                JsonHandler.createJsonFromDevice(device, "NewDevice.json",
                        getApplicationContext());

                Connection connection = new Connection();
                connection.postNewDevice(device, getApplicationContext());


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        date = c.getTime();
        switch (clickedCalendar) {
            case "tuev":
                editTuev.setText(format.format(date));
                break;
            case "uvv":
                editUvv.setText(format.format(date));
                break;
            case "repair":
                editRepair.setText(format.format(date));
                break;
            case "guarantee":
                editGuarantee.setText(format.format(date));
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        {
            new AlertDialog.Builder(this)
                    .setTitle("Änderungen verwerfen")
                    .setMessage("Sind Sie sich sicher, dass Sie die Änderungen verwerfen möchten?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SwitchEditable switchEditable = new SwitchEditable(false);
                            SwitchEditable.createSwitch(switchEditable, getApplicationContext());
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}



package com.example.asset_management.deviceCard.ui.card;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.deviceCard.ui.reservation.DatePickerFragment;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * CardFragment
 * <p>
 *     Version 1.0
 * </p>
 * 17.08.2020
 * AUTHOR: Dominik Dziersan
 */

public class CardFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener {
    private DialogFragment datePicker = new DatePickerFragment();
    private FragmentDeviceCardListener listener;
    String saveMessage = "Informationen wurden geändert.";
    private CardViewModel cardViewModel;
    String clickedCalendar = "";
    private Calendar date = Calendar.getInstance();
    EditText editTuev;
    EditText editUvv;
    EditText editRepair;

    /**
     * Interface to communicate between activities.
     */
    public interface FragmentDeviceCardListener {
        void onInputSent(String input);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                ViewModelProviders.of(this).get(CardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_device_card, container,
                false);

        final EditText editInventoryNumber = root.findViewById(R.id.editInventoryNumber);
//        final EditText editStatus = root.findViewById(R.id.editStatus);
        final Spinner editStatus = root.findViewById(R.id.editStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.status, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        editStatus.setAdapter(adapter);

//        editStatus.setOnItemSelectedListener(this);
        final EditText editManufacturer = root.findViewById(R.id.editManufacturer);
        final EditText editModel = root.findViewById(R.id.editModel);
        final EditText editSerialnumber = root.findViewById(R.id.editSerialnumber);
        final EditText editCategory = root.findViewById(R.id.editCategory);
        final EditText editName = root.findViewById(R.id.editName);
        final EditText editCity = root.findViewById(R.id.editCity);
        final EditText editPostcode = root.findViewById(R.id.editPostcode);
        final EditText editStreet = root.findViewById(R.id.editStreet);
        final EditText editNotes = root.findViewById(R.id.editNotes);
        final EditText editProject = root.findViewById(R.id.editProject);

        editTuev = root.findViewById(R.id.editTuev);
        editUvv = root.findViewById(R.id.editUvv);
        editRepair = root.findViewById(R.id.editRepair);

        final Button btnTuev = root.findViewById(R.id.btnTuev);
        final View viewTuev = root.findViewById(R.id.btnTuev);
        final View viewSave = root.findViewById(R.id.btnSave);
        final Button btnSave = root.findViewById(R.id.btnSave);
        final Button btnUvv = root.findViewById(R.id.btnUvv);
        final View viewUvv = root.findViewById(R.id.btnUvv);
        final Button btnRepair = root.findViewById(R.id.btnRepair);
        final View viewRepair = root.findViewById(R.id.btnRepair);

        btnTuev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "tuev";
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),CardFragment.this,  date
                        .get(Calendar.YEAR), date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnUvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "uvv";
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),CardFragment.this,  date
                        .get(Calendar.YEAR), date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCalendar = "repair";
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),CardFragment.this,  date
                        .get(Calendar.YEAR), date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


            DeviceCardActivity activity = (DeviceCardActivity) getActivity();
            final Device device = activity.getDevice();

            editInventoryNumber.setText(device.getInventoryNumber());
                editStatus.post(new Runnable() {
                    @Override
                    public void run() {
                        editStatus.setSelection(getPosition(device));
                    }
                });

            editManufacturer.setText(device.getManufacturer());
            editModel.setText(device.getModel());
            editSerialnumber.setText(device.getSerialnumber());
            editCategory.setText(device.getCategory());
            editPostcode.setText(device.getPostcode());
            editCity.setText(device.getCity());
            editStreet.setText(device.getStreet());
            editName.setText(device.getName());
            editNotes.setText(device.getNote());
//            editProject.setText(device.getProjectId());
            try {
                editTuev.setText(device.getLastTuev().toString());
            } catch (Exception e){
                editTuev.setText("");
            }
            try {
                editUvv.setText(device.getLastUvv().toString());
            } catch (Exception e){
                editUvv.setText("");
            }
            try {
                editRepair.setText(device.getLastRepair().toString());
            } catch (Exception e){
                editRepair.setText("");
            }
                if (!activity.isClicked()){
                    setInvisibility(editTuev, btnTuev);
                    setInvisibility(editUvv, btnUvv);
                    setInvisibility(editRepair, btnRepair);
                    viewSave.setVisibility(View.INVISIBLE);
                    blockInput(editInventoryNumber);
                    editStatus.setEnabled(false);
                    blockInput(editManufacturer);
                    blockInput(editModel);
                    blockInput(editSerialnumber);
                    blockInput(editCategory);
                    blockInput(editName);
                    blockInput(editCity);
                    blockInput(editStreet);
                    blockInput(editPostcode);
                    blockInput(editTuev);
                    blockInput(editUvv);
                    blockInput(editRepair);
                    blockInput(editProject);
                    blockInput(editNotes);

                } else {
                    setVisibility(editTuev, btnTuev);
                    setVisibility(editUvv, btnUvv);
                    setVisibility(editRepair, btnRepair);
                    unblockInput(editInventoryNumber);
                    viewSave.setVisibility(View.VISIBLE);
                    editStatus.setEnabled(true);
                    unblockInput(editManufacturer);
                    unblockInput(editModel);
                    unblockInput(editSerialnumber);
                    unblockInput(editCategory);
                    unblockInput(editPostcode);
                    unblockInput(editStreet);
                    unblockInput(editCity);
                    unblockInput(editName);
                    blockInput(editTuev);
                    blockInput(editUvv);
                    blockInput(editRepair);
                    unblockInput(editNotes);
                    unblockInput(editProject);
                }

        btnSave.setOnClickListener(new View.OnClickListener() {
            /**
             * Takes the input from all editTexts and saves it into an json file
             */
            @Override
            public void onClick(View v) {
                String jsonName = "DeviceList.json";
                String jsonChangedDevice = "ChangedDevice.json";

                String inventoryNumber = editInventoryNumber.getText().toString();
                String serialnumber = editSerialnumber.getText().toString();
                String manufacturer = editManufacturer.getText().toString();
                String model = editModel.getText().toString();
                String status;
                if(editStatus.getSelectedItem().toString().equals("")){
                    status = "Verfürgbar";
                }
                status = editStatus.getSelectedItem().toString();
                String category = editCategory.getText().toString();
                String name = editName.getText().toString();
                String street = editStreet.getText().toString();
                String city = editCity.getText().toString();
                String notes = editNotes.getText().toString();
                String tuev = editTuev.getText().toString();
                String uvv = editUvv.getText().toString();
                String project = editProject.getText().toString();
                String repair = editRepair.getText().toString();

                DeviceCardActivity activity = (DeviceCardActivity) getActivity();
                Device device = activity.getDevice();

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

                JsonHandler.createJsonFromDevice(device, jsonChangedDevice, getContext());

                SwitchEditable switchEditable = new SwitchEditable(false);
                DeviceCardActivity activityCard = new DeviceCardActivity();
                ((DeviceCardActivity)getActivity()).createSwitch(switchEditable);

                ((DeviceCardActivity)getActivity()).finish();

                Connection connection = new Connection();
                connection.putChangeDevice(device, getContext());

                Toast.makeText(getContext(),saveMessage,Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    /**
     * Blocks the input from all editTexts
     * @param editText
     */
    public static void blockInput(EditText editText){
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
    }


    /**
     * unlock Blocks the input from all editTexts
     * @param editText
     */
    public static void unblockInput(EditText editText){
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
    }

    /**
     * prepares the status from the device for the status spinner position.
     * @param device
     * @return int for the status spinner
     */
    private int getPosition (Device device){

        String status = device.getStatus();
        if (status == null){
            return 0;
        }
        switch(status) {
            case "Verfügbar":
                return 0;
            case "Ausgeliehen":
                return 1;
            case "In Wartung":
                return 2;
            case "Außer Betrieb":
                return 3;
            case "Defekt":
                return 4;
            case "Verschollen/Verschwunden":
                return 5;
            case "Gestohlen":
                return 6;
            default: return 0;
        }
    }

    /**
     * Sets the visibility of an editText
     * @param editText Edittext to change visibility
     * @param view
     */
    public static void setVisibility(EditText editText, View view){
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.setVisibility(View.VISIBLE);
    }
    /**
     * Sets the visibility of an editText
     * @param editText Edittext to change visibility
     * @param view
     */
    public static void setInvisibility(EditText editText, View view){
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        switch(clickedCalendar){
            case"tuev":
                editTuev.setText(currentDateString);
                break;
            case"uvv":
                editUvv.setText(currentDateString);
                break;
            case"repair":
                editRepair.setText(currentDateString);
                break;
            default:
        }
    }

    /**
     * Preparation for the interface to communicate between activities.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDeviceCardListener) {
            listener = (FragmentDeviceCardListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
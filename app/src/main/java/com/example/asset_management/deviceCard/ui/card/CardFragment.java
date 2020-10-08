package com.example.asset_management.deviceCard.ui.card;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
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
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * CardFragment
 * <p>
 *     Version 1.0
 * </p>
 * 17.08.2020
 */
public class CardFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener {
    private DialogFragment datePicker = new DatePickerFragment();
    private FragmentDeviceCardListener listener;
    String saveMessage = "Informationen wurden geändert.";
    private CardViewModel cardViewModel;
    String clickedCalendar = "";
    private Calendar calendar = Calendar.getInstance();
    EditText editTuev;
    EditText editUvv;
    EditText editRepair;
    EditText editGuarantee;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
    Date date = new Date();

    public interface FragmentDeviceCardListener {
        void onInputASent(String input);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                ViewModelProviders.of(this).get(CardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_device_card, container,
                false);

        final EditText editInventoryNumber = root.findViewById(R.id.editInventoryNumber);
        final Spinner editCategory = root.findViewById(R.id.editCategory);
        final Spinner editStatus = root.findViewById(R.id.editStatus);

        ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(getContext(),
                R.array.status, android.R.layout.simple_spinner_item);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(getContext(),
                R.array.category, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_item);

        editStatus.setAdapter(adapterStatus);
        editCategory.setAdapter(adapterCategory);

        final EditText editManufacturer = root.findViewById(R.id.editManufacturer);
        final EditText editModel = root.findViewById(R.id.editModel);
        final EditText editSerialnumber = root.findViewById(R.id.editSerialnumber);
//        final EditText editCategory = root.findViewById(R.id.editCategory);
        final EditText editName = root.findViewById(R.id.editName);
        final EditText editCity = root.findViewById(R.id.editCity);
        final EditText editPostcode = root.findViewById(R.id.editPostcode);
        final EditText editStreet = root.findViewById(R.id.editStreet);
        final EditText editNotes = root.findViewById(R.id.editNotes);
        final EditText editProject = root.findViewById(R.id.editProject);
        final EditText editRepairNotes = root.findViewById(R.id.editRepairNotes);
        final EditText editBeaconMinor = root.findViewById(R.id.editBeaconMinor);
        final EditText editBeaconMajor = root.findViewById(R.id.editBeaconMajor);

        editGuarantee = root.findViewById(R.id.editGuarantee);
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
        final Button btnGuarantee = root.findViewById(R.id.btnGuarantee);
        final View viewGuarantee = root.findViewById(R.id.btnGuarantee);


        cardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            DeviceCardActivity activity = (DeviceCardActivity) getActivity();
            final Device device = activity.getDevice();

            editStatus.post(new Runnable() {
                @Override
                public void run() {
                    editStatus.setSelection(device.getPosition(getContext()));
                }
            });

            editCategory.post(new Runnable() {
                @Override
                public void run() {
                    editCategory.setSelection(device.getPosition(getContext()));
                }
            });

            editInventoryNumber.setText(device.getInventoryNumber());
            editManufacturer.setText(device.getManufacturer());
            editModel.setText(device.getModel());
            editSerialnumber.setText(device.getSerialnumber());
//            editCategory.setText(device.getCategory());
            editPostcode.setText(device.getPostcode());
            editCity.setText(device.getCity());
            editStreet.setText(device.getStreet());
            editName.setText(device.getName());
            editNotes.setText(device.getNote());
            editRepairNotes.setText(device.getRepairNote());
            editBeaconMinor.setText(device.getBeaconMinor());
            editBeaconMajor.setText(device.getBeaconMajor());

            try {
                date = device.getLastTuev();
                editTuev.setText(format.format(date));
            } catch (Exception e){
                editTuev.setText("");
            }
            try {
                date = device.getLastUvv();
                editUvv.setText(format.format(date));
            } catch (Exception e){
                editUvv.setText("");
            }
            try {
                date = device.getLastRepair();
                editRepair.setText(format.format(date));
            } catch (Exception e){
                editRepair.setText("");
            }
            try {
                date = device.getGuarantee();
                editGuarantee.setText(format.format(date));
            } catch (Exception e){
                editGuarantee.setText("");
            }

            if (!SwitchEditable.isClicked(getContext())){
                setInvisibility(editTuev, btnTuev);
                setInvisibility(editUvv, btnUvv);
                setInvisibility(editRepair, btnRepair);
                setInvisibility(editGuarantee, btnGuarantee);
                viewSave.setVisibility(View.INVISIBLE);
                blockInput(editInventoryNumber);
                editStatus.setEnabled(false);
                editCategory.setEnabled(false);
                blockInput(editManufacturer);
                blockInput(editModel);
                blockInput(editSerialnumber);
//                    blockInput(editCategory);
                blockInput(editName);
                blockInput(editCity);
                blockInput(editStreet);
                blockInput(editPostcode);
                blockInput(editTuev);
                blockInput(editUvv);
                blockInput(editRepair);
                blockInput(editProject);
                blockInput(editNotes);
                blockInput(editRepairNotes);
                blockInput(editBeaconMajor);
                blockInput(editBeaconMinor);
                blockInput(editGuarantee);

            } else {
                setVisibility(editTuev, btnTuev);
                setVisibility(editUvv, btnUvv);
                setVisibility(editRepair, btnRepair);
                setVisibility(editGuarantee, btnGuarantee);
                blockInput(editInventoryNumber);
                viewSave.setVisibility(View.VISIBLE);
                editStatus.setEnabled(true);
                editCategory.setEnabled(true);
                unblockInput(editManufacturer);
                unblockInput(editModel);
                unblockInput(editSerialnumber);
//                    unblockInput(editCategory);
                unblockInput(editPostcode);
                unblockInput(editStreet);
                unblockInput(editCity);
                unblockInput(editName);
                blockInput(editTuev);
                blockInput(editUvv);
                blockInput(editRepair);
                unblockInput(editNotes);
                unblockInput(editProject);
                unblockInput(editRepairNotes);
                unblockInput(editBeaconMinor);
                unblockInput(editBeaconMinor);
            }

            btnTuev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedCalendar = "tuev";
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            CardFragment.this,  calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            btnGuarantee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedCalendar = "guarantee";
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            CardFragment.this,  calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            btnUvv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedCalendar = "uvv";
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            CardFragment.this,  calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            btnRepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedCalendar = "repair";
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            CardFragment.this,  calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            }
        });

/**
 * Takes the input from als editTexts and saves it into an json file
 */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonName = getString(R.string.deviceNameJSON);
                String jsonChangedDevice = getString(R.string.changedDeviceNameJSON);

                String inventoryNumber = editInventoryNumber.getText().toString();
                String serialnumber = editSerialnumber.getText().toString();
                String manufacturer = editManufacturer.getText().toString();
                String model = editModel.getText().toString();
                String status;
                if(editStatus.getSelectedItem().toString().equals("")){
                    status = getString(R.string.deviceStatus0);
                } else {
                    status = editStatus.getSelectedItem().toString();
                }

                String category;
                if(editCategory.getSelectedItem().toString().equals("")){
                    status = getString(R.string.deviceCategory8);
                } else {
                    category = editStatus.getSelectedItem().toString();
                }

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

                DeviceCardActivity activity = (DeviceCardActivity) getActivity();
                Device device = activity.getDevice();

                device.setInventoryNumber(inventoryNumber);
                device.setSerialnumber(serialnumber);
                device.setModel(model);
                device.setStatus(status);
                device.setDeviceCategory(device.getPositionCategory(getContext())+1);
                device.setManufacturer(manufacturer);
                device.setNote(notes);
                device.setCity(city);
                device.setStreet(street);
                device.setName(name);
                device.setBeaconMinor(beaconMinor);
                device.setBeaconMajor(beaconMajor);
                device.setRepairNote(repairNote);

                SimpleDateFormat format = new SimpleDateFormat(getString(R.string.datePattern),
                        Locale.GERMAN);
                Date date = null;

                try{
                    device.setProjectId(Integer.parseInt(project));
                } catch (Exception ignored) {

                }
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

                Toast.makeText(getContext(),device.getLastTuev().toString(),Toast.LENGTH_SHORT)
                        .show();

                JsonHandler.createJsonFromDevice(device, jsonChangedDevice, getContext());

                SwitchEditable switchEditable = new SwitchEditable(false);
                DeviceCardActivity activityCard = new DeviceCardActivity();
                SwitchEditable.createSwitch(switchEditable, getContext());

                ((DeviceCardActivity)getActivity()).finish();
                Connection connection = new Connection();
                connection.putChangeDevice(device, getContext());
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
     * Sets the visibility of an button
     * @param editText
     * @param view
     */
    public static void setVisibility(EditText editText, View view){
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.setVisibility(View.VISIBLE);
    }

    /**
     * Sets the visibility of an button
     * @param editText
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

        date = c.getTime();
        switch(clickedCalendar){
            case"tuev":
                editTuev.setText(format.format(date));
                break;
            case"uvv":
                editUvv.setText(format.format(date));
                break;
            case"repair":
                editRepair.setText(format.format(date));
                break;
            case"guarantee":
                editGuarantee.setText(format.format(date));
                break;
            default:
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
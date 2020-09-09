package com.example.asset_management.deviceCard.ui.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.deviceCard.SwitchEditable;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CardFragment
 * <p>
 *     Version 1.0
 * </p>
 * 17.08.2020
 */
public class CardFragment extends Fragment {

    String saveMessage = "Informationen wurden geändert.";
    private CardViewModel cardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                ViewModelProviders.of(this).get(CardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_device_card, container, false);

        final EditText editInventoryNumber = root.findViewById(R.id.editInventoryNumber);
        final EditText editStatus = root.findViewById(R.id.editStatus);
        final EditText editManufacturer = root.findViewById(R.id.editManufacturer);
        final EditText editModel = root.findViewById(R.id.editModel);
        final EditText editSerialnumber = root.findViewById(R.id.editSerialnumber);
        final EditText editCategory = root.findViewById(R.id.editCategory);
        final EditText editName = root.findViewById(R.id.editName);
        final EditText editCity = root.findViewById(R.id.editCity);
        final EditText editPostcode = root.findViewById(R.id.editPostcode);
        final EditText editStreet = root.findViewById(R.id.editStreet);
        final EditText editTuev = root.findViewById(R.id.editTuev);
        final EditText editUvv = root.findViewById(R.id.editUvv);
        final View viewSave = root.findViewById(R.id.btnSave);
        final Button btnSave = root.findViewById(R.id.btnSave);

        cardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            DeviceCardActivity activity = (DeviceCardActivity) getActivity();
            Device device = activity.getDevice();

            editInventoryNumber.setText(device.getInventoryNumber());
            editStatus.setText(device.getStatus());
            editManufacturer.setText(device.getManufacturer());
            editModel.setText(device.getModel());
            editSerialnumber.setText(device.getSerialnumber());
            editCategory.setText(device.getCategory());
            editPostcode.setText(device.getPostcode());
            editCity.setText(device.getCity());
            editStreet.setText(device.getStreet());
            editName.setText(device.getName());
            editTuev.setText(device.getLastTuev().toString());
            editUvv.setText(device.getLastUvv().toString());



                if (!activity.isClicked()){
                    viewSave.setVisibility(View.GONE);
                    blockInput(editInventoryNumber);
                    blockInput(editStatus);
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

                } else {
                    viewSave.setVisibility(View.VISIBLE);
                    unblockInput(editInventoryNumber);
                    unblockInput(editStatus);
                    unblockInput(editManufacturer);
                    unblockInput(editModel);
                    unblockInput(editSerialnumber);
                    unblockInput(editCategory);
                    unblockInput(editPostcode);
                    unblockInput(editStreet);
                    unblockInput(editCity);
                    unblockInput(editName);
                    unblockInput(editTuev);
                    unblockInput(editUvv);
                }
            }
        });

/**
 * Takes the input from als editTexts and saves it into an json file
 */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonName = "DeviceList.json";

                String inventoryNumber = editInventoryNumber.getText().toString();
                String serialnumber = editSerialnumber.getText().toString();
                String manufacturer = editManufacturer.getText().toString();
                String model = editModel.getText().toString();
                String status = editStatus.getText().toString();
                String category = editCategory.getText().toString();

                Device device = new Device(inventoryNumber, serialnumber, manufacturer, model,
                        category, status);

                JsonHandler.createJsonFromObject(device,"ChangedDevice.json", getActivity());

                ArrayList<Device> deviceList = new ArrayList<>();

                try {
                    deviceList = JsonHandler.getDeviceList(jsonName,
                            getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(Device i : deviceList){
                    if(i.getInventoryNumber().equals(device.getInventoryNumber())){
                        i.setCategory(device.getCategory());
                        i.setManufacturer(device.getManufacturer());
                        i.setStatus(device.getStatus());
                        i.setModel(device.getModel());
                        i.setSerialnumber(device.getSerialnumber());
                    }
                }
                JsonHandler.createJsonFromDeviceList(deviceList,jsonName,getActivity());

                SwitchEditable switchEditable = new SwitchEditable(false);
                DeviceCardActivity activityCard = new DeviceCardActivity();
                ((DeviceCardActivity)getActivity()).createSwitch(switchEditable);

                ((DeviceCardActivity)getActivity()).refreshUI();

                Toast.makeText(getContext(),saveMessage,Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    /**
     * Blocks the input from all editTexts
     * @param editText
     */
    private void blockInput(EditText editText){
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
//        editText.setKeyListener(null);
    }

    /**
     * unlock Blocks the input from all editTexts
     * @param editText
     */
    private void unblockInput(EditText editText){
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
//        editText.setKeyListener();
    }
}
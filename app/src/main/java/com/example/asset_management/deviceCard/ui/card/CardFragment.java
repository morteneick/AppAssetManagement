package com.example.asset_management.deviceCard.ui.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.recycleView.Device;

import java.io.IOException;

public class CardFragment extends Fragment {

    private CardViewModel cardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                ViewModelProviders.of(this).get(CardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_device_card, container, false);

        final EditText editInventoryNumber = root.findViewById(R.id.editInventoryNumber);
        final EditText editStatus = root.findViewById(R.id.editStatus);
        final EditText editLocation = root.findViewById(R.id.editLocation);
        final EditText editManufacturer = root.findViewById(R.id.editManufacturer);
        final EditText editModel = root.findViewById(R.id.editModel);
        final EditText editSerialnumber = root.findViewById(R.id.editSerialnumber);
        final EditText editCategory = root.findViewById(R.id.editCategory);

        cardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            DeviceCardActivity activity = (DeviceCardActivity) getActivity();
            Device device = activity.getDevice();

            editInventoryNumber.setText(device.getInventoryNumber());
            editStatus.setText(device.getStatus());
            editLocation.setText(device.getLocation());
            editManufacturer.setText(device.getManufacturer());
            editModel.setText(device.getModel());
            editSerialnumber.setText(device.getSerialnumber());
            editCategory.setText(device.getCategory());


                if (!activity.isClicked()){
                    blockInput(editInventoryNumber);
                    blockInput(editStatus);
                    blockInput(editLocation);
                    blockInput(editManufacturer);
                    blockInput(editModel);
                    blockInput(editSerialnumber);
                    blockInput(editCategory);

                } else {
                    unblockInput(editInventoryNumber);
                    unblockInput(editStatus);
                    unblockInput(editLocation);
                    unblockInput(editManufacturer);
                    unblockInput(editModel);
                    unblockInput(editSerialnumber);
                    unblockInput(editCategory);
                }
            }
        });
        return root;
    }

    /**
     *
     * @param editText
     */
    private void blockInput(EditText editText){
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
//        editText.setKeyListener(null);
    }

    /**
     *
     * @param editText
     */
    private void unblockInput(EditText editText){
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
//        editText.setKeyListener();
    }
}
package com.example.asset_management.deviceCard.ui.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.recycleView.Device;

public class CardFragment extends Fragment {

    private CardViewModel cardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                ViewModelProviders.of(this).get(CardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_device_card, container, false);
        final EditText editInventoryNumber = root.findViewById(R.id.editInventoryNumber);
        cardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                DeviceCardActivity activity = (DeviceCardActivity) getActivity();
                Device device = activity.getDevice();
                editInventoryNumber.setText(device.getInventoryNumber());

                blockInput(editInventoryNumber);

            }
        });
        return root;
    }

    private void blockInput(EditText editText){
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
//        editText.setKeyListener(null);
    }

    private void unblockInput(EditText editText){
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
//        editText.setKeyListener();
    }

}
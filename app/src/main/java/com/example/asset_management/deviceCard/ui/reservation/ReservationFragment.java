package com.example.asset_management.deviceCard.ui.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;

import java.util.ArrayList;
/**
 * ReservationFragment
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 */
public class ReservationFragment extends Fragment {

    private ArrayList<String> arrayList = new ArrayList<String>();

    private ReservationViewModel reservationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reservationViewModel =
                ViewModelProviders.of(this).get(ReservationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_device_card_reservation, container,
                false);

        reservationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        Button btnStartReserve = root.findViewById(R.id.btnReserveActivity);
        btnStartReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReservationActivity.class);
                startActivity(intent);
                DeviceCardActivity activity = (DeviceCardActivity) getActivity();
                Device device = activity.getDevice();
                ArrayList<Device> list = new ArrayList<>();
                list.add(device);
                JsonHandler.createJsonFromDeviceList(list, "ReservationDevice.json", getContext());
            }
        });


        ListView listView = root.findViewById(R.id.listView);
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");
        arrayList.add("18. September 2020 - 1, Oktober 2020");


        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
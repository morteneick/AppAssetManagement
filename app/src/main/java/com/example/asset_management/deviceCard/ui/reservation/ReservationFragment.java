package com.example.asset_management.deviceCard.ui.reservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;

import java.io.IOException;
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
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            list = JsonHandler.getCalendarList("ReservationList.json", getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeviceCardActivity activity = (DeviceCardActivity) getActivity();
        Device device = activity.getDevice();


        for(Reservation r : list){
            if(r.getInventoryNumber().toString().equals(device.getInventoryNumber()) )
            arrayList.add(r.getLoanDay() + " - " + r.getLoanEnd());
        }

        if(arrayList.size() == 0){
            arrayList.add("Keine Reservierungen gefunden");
        }
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");
//        arrayList.add("18. September 2020 - 1, Oktober 2020");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                new AlertDialog.Builder(getContext())
                        .setTitle("Reservierung löschen")
                        .setMessage("Sind Sie sich sicher, dass Sie die Reservierung löschen möchten?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Connection connection = new Connection();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        return root;
    }
}
package com.example.asset_management.deviceCard.ui.reservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.mainHub.MainHubActivity;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

        final String reservationDeviceName = getString(R.string.reservationListNameJSON);
        reservationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        ListView listView = root.findViewById(R.id.listView);
        ArrayList<Reservation> list = new ArrayList<>();
        final ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
            list = JsonHandler.getReservationList(reservationDeviceName, getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DeviceCardActivity activity = (DeviceCardActivity) getActivity();

        final Device device = activity.getDevice();

        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.datePattern));
        for(Reservation r : list){
            if(r.getInventoryNumber().toString().equals(device.getInventoryNumber()) )
            try {
                arrayList.add(r.getBuildingSite() + " " + format.format(r.getLoanDay()) + " - "
                        + format.format(r.getLoanEnd()));
            } catch (Exception ignored){

            }

            listReservation.add(r);
        }

        if(arrayList.size() == 0){
            arrayList.add(getString(R.string.noReservationFoundText));
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(itemsAdapter);

        UserInfo user = MainHubActivity.getUser();
        if(user.intToBool(user.getDeleteBooking())){
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.deleteReservationTitle))
                            .setMessage(getString(R.string.deleteReservationText))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Connection connection = new Connection();
                                    Reservation deleteReservation = listReservation.get(position);
                                    connection.deleteReservation(device, deleteReservation,
                                            getContext());
                                    ArrayList<Reservation> list = new ArrayList<Reservation>();
                                    ((DeviceCardActivity)getActivity()).refreshUI();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return false;
                }
            });
        } else {
            user.noAccessMessage(getContext());
        }

        Button btnStartReserve = root.findViewById(R.id.btnReserveActivity);
        if(user.intToBool(user.getBookingDevice())){
            btnStartReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReservationActivity.class);
                    startActivity(intent);
                    DeviceCardActivity activity = (DeviceCardActivity) getActivity();
                    Device device = activity.getDevice();
                    ArrayList<Device> list = new ArrayList<>();
                    list.add(device);
                    JsonHandler.createJsonFromDeviceList(list, reservationDeviceName, getContext());
                }
            });
        } else {
            user.noAccessMessage(getContext());
        }


        return root;
    }
}
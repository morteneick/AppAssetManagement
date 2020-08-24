package com.example.asset_management.deviceCard.ui.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asset_management.R;

import java.util.ArrayList;

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
            }
        });


        ListView listView = (ListView) root.findViewById(R.id.listView);
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


        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(itemsAdapter);
        return root;
    }
}
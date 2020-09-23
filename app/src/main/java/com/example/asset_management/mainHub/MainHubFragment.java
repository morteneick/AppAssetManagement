package com.example.asset_management.mainHub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity;
import com.example.asset_management.recycleViewUserList.UserRecycleActivity;
import com.example.asset_management.settings.SettingsActivity;

/**
 * MainHubFragment
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class MainHubFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainHubFragment.this)
                        .navigate(R.id.action_FirstFragment_to_addDeviceActivity);
            }
        });

        view.findViewById(R.id.btnInventory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainHubFragment.this)
                        .navigate(R.id.action_FirstFragment_to_recycleActivity);
            }
        });

        view.findViewById(R.id.btnScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainHubFragment.this)
                        .navigate(R.id.action_FirstFragment_to_scanDeviceActivity);
            }
        });
        view.findViewById(R.id.btnHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainHubFragment.this)
                        .navigate(R.id.action_FirstFragment_to_deviceHistoryActivity);
            }
        });

        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent(getActivity(),
                        DeviceRecycleActivity.class);
                startActivity(intent);

            }
        });
        view.findViewById(R.id.btnSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainHubFragment.this)
                        .navigate(R.id.action_FirstFragment_to_settingsActivity);

            }
        });
    }
}

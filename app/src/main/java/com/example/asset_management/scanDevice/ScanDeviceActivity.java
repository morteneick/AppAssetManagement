package com.example.asset_management.scanDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asset_management.R;

import android.content.Context;
import android.os.Vibrator;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

//import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ScanDeviceActivity
 * Version 1.1
 * 31.07.2020
 * AUTHOR: Morten Eickmann
 */

public class ScanDeviceActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    private String detectedCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_device);

        surfaceView = findViewById(R.id.camerapreview);
        textView = findViewById(R.id.textView);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480).setAutoFocusEnabled(true).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("MissingPermission")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }
            /**
             * Method to display the content of the detected QR Code.
             * The Device vibrates, when a code has been detected.
             * Saving the detected code in variable "detectedCode".
             */
            @Override
            public void receiveDetections(final Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if (qrCodes.size() != 0) {
                    textView.post(new Runnable() {
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService
                                    (Context.VIBRATOR_SERVICE);
                            detectedCode = qrCodes.valueAt(0).displayValue;

                            textView.setText(detectedCode);
                            try{
                                Device device = new Device();
                                int i = 0;

                                ArrayList<Device> devices = JsonHandler.getDeviceList
                                        (getString(R.string.deviceListNameJSON)
                                                , getApplicationContext());
                                for(Device d : devices){
                                    assert d.getInventoryNumber() != null;
                                    if(d.getInventoryNumber().equals(detectedCode)){
                                        device = d;
                                        i++;
                                    }
                                }
                                if(i == 1){
                                    Intent intent = new Intent(getApplicationContext(),
                                            DeviceCardActivity.class);
                                    intent.putExtra(getString(R.string.deviceName), device);
                                    intent.putExtra(getString(R.string.isOldVersion), false);
                                    startActivity(intent);
                                }
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.failureText),
                                        Toast.LENGTH_SHORT).show();
                            }

                            Connection connection = new Connection();
                            connection.getDeviceList(getApplicationContext());
                           // if (detectedCode.equals())

                        }
                    });
                }
            }
        });
    }
}
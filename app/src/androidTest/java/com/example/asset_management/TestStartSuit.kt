package com.example.asset_management

import com.example.asset_management.addDevice.AddDeviceFragmentTest
import com.example.asset_management.jsonhandler.JsonHandlerTest
import com.example.asset_management.mainHub.MainHubActivityTest
import com.example.asset_management.recycleViewDeviceList.DeviceAdapterTest
import com.example.asset_management.recycleViewDeviceList.RecycleFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        JsonHandlerTest::class,
        AddDeviceFragmentTest::class,
        RecycleFragmentTest::class,
        DeviceAdapterTest::class,
        MainHubActivityTest::class
)
class TestStartSuit {
}
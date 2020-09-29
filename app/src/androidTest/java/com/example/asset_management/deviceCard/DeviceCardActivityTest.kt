package com.example.asset_management.deviceCard

import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.asset_management.R
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before


@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceCardActivityTest {
    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @Test
    fun testIsActivityInView() {
        val activityScenario = ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.activity_devicecard)).check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testToolbar() {
        val activityScenario = ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());


    }
    @Test
    fun testCheckSettingsClicked() {
        val activityScenario = ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Bearbeiten")).perform(click());


    }
}
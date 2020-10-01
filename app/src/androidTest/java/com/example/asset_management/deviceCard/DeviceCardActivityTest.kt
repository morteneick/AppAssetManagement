package com.example.asset_management.deviceCard

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.example.asset_management.R
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceCardActivityTest {


    @Test
    fun testIsActivityInView() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.activity_devicecard)).check(ViewAssertions.matches(isDisplayed()))

    }


    @Test
    fun testToolbar() {
        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());


    }
    @Test
    fun testToolbarSettings() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Bearbeiten")).perform(click());
        Assert.assertTrue(SwitchEditable.isClicked(getInstrumentation().getTargetContext()))

    }

    @Test
    fun testToolbarVersions() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Zeige Verlauf")).perform(click());
        assertFalse(activityScenario.equals(DeviceCardOldVersionsListActivity::class.java))
    }

    @Test
    fun testToolbarDelete() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Löschen")).perform(click());

        onView(withText("OK")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    fun testMapInView() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.navigation_map)).perform(click())
        onView(withId(R.id.fragment_map)).check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testReservationInView() {

        val activityScenario =
                ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.navigation_reservation)).perform(click())
        onView(withId(R.id.fragment_reservation)).check(ViewAssertions.matches(isDisplayed()))

    }



}
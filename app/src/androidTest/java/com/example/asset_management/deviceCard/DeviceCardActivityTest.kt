package com.example.asset_management.deviceCard

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.asset_management.R
import com.example.asset_management.login.LoginActivity
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import junit.framework.Assert.assertFalse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceCardActivityTest {


    @Test
    fun testIsActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.activity_devicecard)).check(ViewAssertions.matches(isDisplayed()))
    }


    @Test
    fun testToolbar() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());


    }
    @Test
    fun testToolbarSettings() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Bearbeiten")).perform(click());
        Assert.assertTrue(SwitchEditable.isClicked(getInstrumentation().getTargetContext()))

    }

    @Test
    fun testToolbarVersions() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Versionsverlauf")).perform(click());
        assertFalse(activityScenario.equals(DeviceCardOldVersionsListActivity::class.java))
    }

    @Test
    fun testToolbarDelete() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Löschen")).perform(click());

        onView(withText("OK")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    fun testMapInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.navigation_map)).perform(click())
        onView(withId(R.id.fragment_map)).check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testReservationInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        onView(withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        onView(withText("Einloggen")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.btnInventory)).perform(click())

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.navigation_reservation)).perform(click())
        onView(withId(R.id.fragment_reservation)).check(ViewAssertions.matches(isDisplayed()))

    }
}
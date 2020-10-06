package com.example.asset_management.deviceCard

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asset_management.R
import com.example.asset_management.login.LoginActivity
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import com.example.asset_management.recycleViewDeviceList.DeviceRecycleActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceCardOldVersionsListActivityTest{

    @Test
    fun testIsListActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Versionsverlauf")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.activity_device_card_old_version_list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testIsActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Versionsverlauf")).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("2020-10-06")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.activity_device_card_old_versions))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testIsActivityMapInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Versionsverlauf")).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("2020-10-06")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.navigation_map)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment_map)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))
    }

}



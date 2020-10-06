package com.example.asset_management.recycleViewDeviceList

import android.os.SystemClock
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import com.example.asset_management.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceHistoryActivityTest{
    @Test
    fun testIsHistoryActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);

        Espresso.onView(ViewMatchers.withId(R.id.btnHistory)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_devicecard))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testIsHistoryActivityClickedCorrectly() {

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

        val text = Espresso.onView(ViewMatchers.withId(R.id.editInventoryNumber)).toString()

        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.btnHistory)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.editInventoryNumber))
                .check(ViewAssertions.matches(ViewMatchers.withText("159742")));

    }
}